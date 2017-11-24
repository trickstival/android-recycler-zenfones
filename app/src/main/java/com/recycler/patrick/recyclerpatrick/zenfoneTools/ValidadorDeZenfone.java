package com.recycler.patrick.recyclerpatrick.zenfoneTools;

import android.content.Context;

import com.recycler.patrick.recyclerpatrick.R;
import com.recycler.patrick.recyclerpatrick.model.Zenfone;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by trickstival on 23/11/17.
 */

public class ValidadorDeZenfone {
    //Mensagem de erro setada pela função validarZenfone
    private static String err_msg = "";

    //Função que valida o objeto zenfone
    public static boolean validarZenfone(Context ctx, Zenfone zenfone){

        Integer ano = zenfone.getAno(),
                anoAtual = Calendar.getInstance().get(Calendar.YEAR)
                        ;
        String nome = zenfone.getNome(),
                modelo = zenfone.getModelo();

        boolean anoValido = ano != null && 2010 <= ano && ano <= anoAtual,
                nomeValido = nome != null && !nome.isEmpty() && nome.length() > 0 && nome.length() <= 30,
                modeloValido = new ArrayList<String>(Arrays.asList(ctx.getResources().getStringArray(R.array.zenfones))).indexOf(modelo) != -1
                        ;
        /* Definindo mensagem de erro. Caso não haja erro, deixe como "" */
        err_msg = !anoValido
                ? "O ano informado é inválido. Por favor, informe um ano entre 2010 e " + anoAtual + "."
                : !nomeValido
                ? "O nome informado é inválido. Por favor informe um nome com 1 a 30 caracteres."
                : !modeloValido
                ? "O modelo informado é inválido. Por favor selecione um dos modelos sugeridos."
                : "";

        //Resultado final
        return anoValido && nomeValido && modeloValido;
    }

    public static String getErr_msg(){
        return err_msg;
    }
}
