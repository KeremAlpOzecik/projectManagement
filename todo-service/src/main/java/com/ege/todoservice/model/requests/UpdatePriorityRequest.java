package com.ege.todoservice.model.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdatePriorityRequest {

        private String priorityName;
        private Boolean priorityStatus;
}
