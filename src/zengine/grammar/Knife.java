/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zengine.grammar;

import zengine.Noun;

public class Knife extends Noun
{
        public Knife()
        {
                super("KNIFE");
                plural = false;
                acceptable.add(zengine.None.class);
        }
}
