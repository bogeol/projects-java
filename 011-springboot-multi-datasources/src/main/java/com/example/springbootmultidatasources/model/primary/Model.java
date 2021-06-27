package com.example.springbootmultidatasources.model.primary;

import lombok.*;
import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "model")
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name = "model_name")
    String modelName;

    @Column(name = "model_description")
    String modelDescription;

    @Column(name = "model_status")
    String modelStatus;
}
