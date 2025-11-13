package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

@Ser
public abstract class cou implements Iterable {
   private static final ILogger xK = GlobalLog.getLogger(cou.class);
   @SerId(1)
   protected String q;
   @SerId(2)
   protected List RF;

   public cou(String var1, int var2) {
      this.q = var1;
      this.RF = new ArrayList(var2);
   }

   public String q() {
      return this.q;
   }

   public int RF() {
      return this.RF.size();
   }

   public int xK() {
      int var1 = 0;

      for (cox var3 : this.RF) {
         if (var3.gP()) {
            var1++;
         }
      }

      return var1;
   }

   public int Dw() {
      int var1 = 0;

      for (cox var3 : this.RF) {
         if (var3.nf()) {
            var1++;
         }
      }

      return var1;
   }

   public void q(cox var1) {
      var1.RF(this.RF.size());
      this.RF.add(var1);
   }

   public cox q(int var1) {
      return (cox)this.RF.get(var1);
   }

   public List Uv() {
      return this.RF;
   }

   @Override
   public Iterator iterator() {
      return this.RF.iterator();
   }

   public Iterable oW() {
      return new cov(this);
   }

   public Iterable gO() {
      return new cow(this);
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d", this.q, this.RF.size());
   }

   class eo implements Iterator {
      int q;
      int RF;

      eo(int var2) {
         this.q = var2;
         this.RF = -1;
         this.q();
      }

      void q() {
         this.RF++;

         for (; this.RF < cou.this.RF.size(); this.RF++) {
            cox var1 = (cox)cou.this.RF.get(this.RF);
            if (this.q == 0) {
               if (var1.gP()) {
                  break;
               }
            } else {
               if (this.q != 1) {
                  throw new RuntimeException();
               }

               if (var1.nf()) {
                  break;
               }
            }
         }
      }

      @Override
      public boolean hasNext() {
         return this.RF < cou.this.RF.size();
      }

      public cox RF() {
         if (this.RF > cou.this.RF.size()) {
            throw new NoSuchElementException();
         } else {
            cox var1 = (cox)cou.this.RF.get(this.RF);
            this.q();
            return var1;
         }
      }
   }
}
