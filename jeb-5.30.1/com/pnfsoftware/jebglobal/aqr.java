package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.cfg.BasicBlock;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class aqr implements Iterable {
   private List q = new ArrayList();

   public aqr() {
   }

   public aqr(aqr var1) {
      for (BasicBlock var3 : var1) {
         this.q.add(var3);
      }
   }

   public BasicBlock q(int var1) {
      return (BasicBlock)this.q.get(var1);
   }

   public BasicBlock q() {
      return this.q.size() > 0 ? (BasicBlock)this.q.get(this.q.size() - 1) : null;
   }

   public List RF() {
      return this.q;
   }

   public void q(BasicBlock var1) {
      this.q.add(var1);
   }

   public void RF(int var1) {
      this.q.remove(var1);
   }

   public int xK() {
      return this.q.size();
   }

   public List Dw() {
      ArrayList var1 = new ArrayList();

      for (BasicBlock var3 : this.q) {
         var1.addAll(var3.getInstructions());
      }

      return var1;
   }

   @Override
   public Iterator iterator() {
      return this.q.iterator();
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (BasicBlock var3 : this.q) {
         Strings.ff(var1, "BB_%08x ", var3.getFirstAddress());
      }

      return var1.toString();
   }
}
