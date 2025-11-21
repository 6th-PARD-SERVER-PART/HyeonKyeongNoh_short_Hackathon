package com.shorthackathon.server.pard_short_hackathon.entity; // Schedule 엔티티와 같은 패키지 가정

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter // @IdClass 사용 시 Setter가 필요할 수 있습니다.
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true) // 💡 중요: equals와 hashCode 구현
public class ScheduleId implements Serializable { // 💡 중요: Serializable 구현 필수

    // Schedule 엔티티의 @Id 필드와 동일한 타입 및 이름으로 정의
    private String userName;

    private LocalDateTime time;

    // 참고: @IdClass를 사용하면 여기에 @Column이나 @Embeddable은 붙이지 않습니다.
    // 필드 정의는 Schedule 엔티티에 위임합니다.
}