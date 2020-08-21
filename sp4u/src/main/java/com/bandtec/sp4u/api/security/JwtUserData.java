package com.bandtec.sp4u.api.security;

import com.bandtec.sp4u.domain.entities.Usuario;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.util.Date;

@Getter
@Setter
public class JwtUserData {

    private JwtUserData() {
    }

    public static class UserData {
        private static String nomeCompleto;
        private static String nomeSocial;
        private static Date dataNascimento;
        private static String email;
        private static String senha;

        public static void clearData(){
            nomeCompleto = null;
            nomeSocial = null;
            dataNascimento = null;
            email = null;
            senha = null;
        }

        public static void fillData(Usuario user){
            setNomeCompleto(user.getNomeCompleto());
            setNomeSocial(user.getNomeSocial());
            setDataNascimento(user.getDataNascimento());
            setEmail(user.getEmail());
            setSenha(user.getSenha());
        }

        public static String getNomeCompleto() {
            return nomeCompleto;
        }

        public static void setNomeCompleto(String nomeCompleto) {
            UserData.nomeCompleto = nomeCompleto;
        }

        public static String getNomeSocial() {
            return nomeSocial;
        }

        public static void setNomeSocial(String nomeSocial) {
            UserData.nomeSocial = nomeSocial;
        }

        public static Date getDataNascimento() {
            return dataNascimento;
        }

        public static void setDataNascimento(Date dataNascimento) {
            UserData.dataNascimento = dataNascimento;
        }

        public static String getEmail() {
            return email;
        }

        public static void setEmail(String email) {
            UserData.email = email;
        }

        public static String getSenha() {
            return senha;
        }

        public static void setSenha(String senha) {
            UserData.senha = senha;
        }
    }


}
