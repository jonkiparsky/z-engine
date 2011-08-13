/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gamefiles.grammar;

import zengine.Noun;

public class Banana extends Noun
{
        public Banana()
        {
                super("BANANA");
                plural = false;
                acceptable.add(None.class);
        }
}
