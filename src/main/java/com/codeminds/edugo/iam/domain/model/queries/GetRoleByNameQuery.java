package com.codeminds.edugo.iam.domain.model.queries;

import com.codeminds.edugo.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}