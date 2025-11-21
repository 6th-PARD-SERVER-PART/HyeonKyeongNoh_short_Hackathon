# 📌 API Documentation

## 📂 Entity 구조

### **Schedule**

| 필드       | 타입            | 설명                   |
| -------- | ------------- | -------------------- |
| userName | String        | **unique**, not null |
| time     | LocalDateTime | not null             |

---

### **Rule**

| 필드        | 타입      | 설명                   |
| --------- | ------- | -------------------- |
| ruleId    | Long    | **unique**, not null |
| memo      | String  | not null             |
| available | boolean | not null             |

---

### **Vote**

| 필드         | 타입      | 설명                   |
| ---------- | ------- | -------------------- |
| ruleId     | Long    | **unique**, not null |
| trueCheck  | Integer | not null             |
| falseCheck | Integer | not null             |
| available  | boolean | not null             |

---

### **LogVote**

| 필드       | 타입     | 설명                   |
| -------- | ------ | -------------------- |
| ruleId   | Long   | **unique**, not null |
| userName | String | **unique**, not null |

---

### **Rating**

| 필드         | 타입        | 설명                   |
| ---------- | --------- | -------------------- |
| userName   | String    | **unique**, not null |
| targetName | String    | **unique**, not null |
| rate       | float     | not null             |
| whenPut    | LocalDate | not null             |

---

---

# 📅 Schedule API

## ➕ POST /schedule

**설명:** 일정 등록

**Request Body (ScheduleDto.ScheduleInfo)**

```json
[
  {
    "userName": "String",
    "time": "2025-11-13T15:23:59"
  }
]
```

**Response:**

* 200 성공
* 실패 시 에러 코드

---

## ✏️ PATCH /schedule/{userName}

**설명:** 특정 인물의 일정 수정
변경된 **전체 time 리스트** 전달

**Request Body (ScheduleDto.ScheduleTimeInfo)**

```json
[
  {
    "time": "2025-11-13T15:23:59"
  }
]
```

---

## 📥 GET /schedule

**설명:** 전체 일정 조회

**Response**

```json
[
  {
    "userName": "String",
    "time": "2025-11-13T15:23:59"
  }
]
```

---

## 📥 GET /schedule/{userName}

**설명:** 특정 인물 일정 조회

**Response**

```json
[
  {
    "time": "2025-11-13T15:23:59"
  }
]
```

---

---

# 👥 Member API

## 📥 GET /member

**설명:** 구성원 목록

**Response**

```json
[
  {
    "userName": "String"
  }
]
```

---

---

# 📜 Rule API

## ➕ POST /rule

**설명:** 규칙 등록

**Request Body (RuleDto.PostInfo)**

```json
{
  "memo": "String"
}
```

**Response (VoteDto.VoteResInfo)**

```json
{
  "ruleId": 123,
  "trueCheck": 0,
  "falseCheck": 0,
  "available": false
}
```

---

## 📥 GET /rule

**설명:** 전체 규칙 조회

**Response**

```json
[
  {
    "ruleId": 123,
    "memo": "String",
    "available": true
  }
]
```

---

---

# 🗳️ Vote API

## ✔️ POST /vote/check

**설명:** 규칙 투표 처리
투표 시 rule 과반수 여부 체크 & available 업데이트

**Request Body (VoteDto.VoteReqInfo)**

```json
{
  "ruleId": 123,
  "userName": "String",
  "isCheck": true
}
```

**Response:**
200 성공 또는 실패 코드

---

## 🔎 GET /vote

**설명:** 특정 rule에 대해 user가 투표했는지 확인

Query:
`?userName=String&ruleId=123`

**Response:**

* 200 → 투표한 적 없음
* 301 → 투표한 적 있음

---

## 📥 GET /vote/all

**설명:** 투표 진행 중인 모든 항목 조회

**Response**

```json
[
  {
    "ruleId": 123,
    "trueCheck": 0,
    "falseCheck": 0,
    "available": true
  }
]
```

---

---

# ⭐ Rating API

## ➕ POST /rating

**설명:** 특정 인물에게 별점 부여

**Request Body (RatingDto.RatingReq)**

```json
{
  "userName": "String",
  "targetName": "String",
  "rate": 0.1
}
```

**Response:**
200 또는 실패

---

## 🔎 GET /rating

**설명:** 최근 1개월 이내 평가 여부 확인

Query:
`?userName=String&targetName=String`

**Response:**

* 200 → 최근 한 달 평가 없음
* 301 → 최근 한 달 평가 있음

---

## 📥 GET /rating/{userName}

**설명:** 특정 인물의 평균 별점 조회

**Response**

```json
{
  "rate": 0.1
}
```

---

---

# ⚙️ 서버 로직 주요 규칙

### ✔️ 1) vote/check 요청마다 rule의 과반수 여부 체크

* trueCheck 또는 falseCheck 중 **과반 초과 시 rule 상태 변경**

### ✔️ 2) vote/check 요청 시 LogVote 레코드 없으면 자동 생성

* **삭제 투표 API 없음**
* delete는 false 투표로 판단

### ✔️ 3) available 상태 변경 로직

* 과반 → rule available = true
* 삭제 과반 → rule 제거 또는 available=false 처리

---
