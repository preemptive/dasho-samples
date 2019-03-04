/* Copyright 2018 PreEmptive Solutions, LLC. All Rights Reserved.
 *
 * This source is subject to the Microsoft Public License (MS-PL).
 * Please see the LICENSE.txt file for more information.
 */
package com.example.spring.beans;

import com.example.spring.other.MyConstants;
import com.example.spring.util.Output;

/**
 * A bean used initialized with constants from the MyConstants interface.
 */
public class ConstantBean extends OutputBean implements MyConstants {

    public ConstantBean() {
        this(QUESTION_2, ANSWER_3);
    }

    public ConstantBean(String question, int answer) {
        super();
        this.question = question;
        this.answer = answer;
    }

    /**
     * Outputs the values of each property
     */
    @Override
    public void output() {
        super.output();
        String ques = getQuestion();
        Output.println("Question: " + ques);
        int ans = getAnswer();
        Output.println("Answer: " + ans);
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String question;
    private int answer;


}
