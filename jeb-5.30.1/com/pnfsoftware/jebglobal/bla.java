package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.collect.CollectionUtil;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.List;

@Ser
public class bla {
   @SerId(1)
   int q;
   @SerId(2)
   int RF;
   @SerId(3)
   List xK;
   @SerId(4)
   List Dw;
   @SerId(5)
   List Uv;
   @SerId(6)
   List oW;

   bla(int var1) {
      this.q = var1;
      this.RF = -1;
      this.xK = new ArrayList();
      this.Dw = new ArrayList();
      this.Uv = new ArrayList();
      this.oW = new ArrayList();
   }

   public boolean q() {
      return this.RF < 0;
   }

   public int RF() {
      return this.q;
   }

   public int xK() {
      return this.RF;
   }

   public List Dw() {
      return this.xK;
   }

   public List Uv() {
      return this.Dw;
   }

   public List oW() {
      ArrayList var1 = new ArrayList(this.xK);
      if (this.RF >= 0) {
         var1.add(this.RF);
      }

      return var1;
   }

   public List gO() {
      return this.Uv;
   }

   public List nf() {
      return this.oW;
   }

   boolean q(bla var1) {
      if (this.q != var1.q) {
         return false;
      } else if (this.RF != var1.RF) {
         return false;
      } else if (!CollectionUtil.compare(this.xK, var1.xK, false)) {
         return false;
      } else if (!CollectionUtil.compare(this.Dw, var1.Dw, false)) {
         return false;
      } else {
         return !CollectionUtil.compare(this.Uv, var1.Uv, false) ? false : CollectionUtil.compare(this.oW, var1.oW, false);
      }
   }
}
