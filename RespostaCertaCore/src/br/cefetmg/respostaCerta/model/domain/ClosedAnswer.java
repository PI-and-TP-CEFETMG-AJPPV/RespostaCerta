/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.cefetmg.respostaCerta.model.domain;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author umcan
 */
@Entity
@DiscriminatorValue(value = "F")
public class ClosedAnswer extends QuestionAnswer implements Serializable{
    private int resposta;
    @ManyToOne
    private ClosedQuestion questao;

    public ClosedAnswer() {
    }

    public ClosedAnswer(int resposta, User autor, ClosedQuestion questao, LocalDate dataResposta, char idtResposta, boolean correta) {
        super(autor, dataResposta, idtResposta, correta);
        this.questao=questao;
        this.resposta = resposta;
    }

    public int getResposta() {
        return resposta;
    }

    public void setResposta(int resposta) {
        this.resposta = resposta;
    }

    public ClosedQuestion getQuestao() {
        return questao;
    }

    public void setQuestao(ClosedQuestion questao) {
        this.questao = questao;
    }
    
    
}
