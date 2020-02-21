package com.david.deliverypizza.specification;

import com.david.deliverypizza.model.QUsuario;
import com.david.deliverypizza.model.Usuario;
import com.david.deliverypizza.repository.UsuarioRepository;
import com.david.deliverypizza.validation.BasicSpecification;
import com.david.deliverypizza.validation.Specifications;
import com.querydsl.core.BooleanBuilder;

public class UsuarioSpecification {

    public static BasicSpecification<Usuario> checkEmailDuplicado(UsuarioRepository repository) {
        return Specifications.basic("Já existe um usuário registrado com este e-mail", (candidate, specification) -> {

            final BooleanBuilder builder = new BooleanBuilder();
            builder.and(QUsuario.usuario.email.eq(candidate.getEmail()));

            if (candidate.getId() != null) {
                builder.and(QUsuario.usuario.id.ne(candidate.getId()));
            }

            return !repository.exists(builder);
        });
    }
}