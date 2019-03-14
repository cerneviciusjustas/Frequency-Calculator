/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home_work;

import java.util.List;

/**
 *
 * @author Justas
 */
public class ValueHolder<T> {

    List<T> value1;
    List<T> value2;

    public ValueHolder(List<T> value1, List<T> value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public List<T> getValue1() {
        return value1;
    }

    public List<T> getValue2() {
        return value2;
    }
}
