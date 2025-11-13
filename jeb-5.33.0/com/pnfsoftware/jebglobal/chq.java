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
public abstract class chq implements Iterable {
   private static final ILogger kS = GlobalLog.getLogger(chq.class);
   @SerId(1)
   protected String pC;
   @SerId(2)
   protected List A;

   public chq(String var1, int var2) {
      this.pC = var1;
      this.A = new ArrayList(var2);
   }

   public int pC() {
      return this.A.size();
   }

   public int A() {
      int var1 = 0;

      for (cht var3 : this.A) {
         if (var3.E()) {
            var1++;
         }
      }

      return var1;
   }

   public int kS() {
      int var1 = 0;

      for (cht var3 : this.A) {
         if (var3.UT()) {
            var1++;
         }
      }

      return var1;
   }

   public void pC(cht var1) {
      var1.pC(this.A.size());
      this.A.add(var1);
   }

   public cht pC(int var1) {
      return (cht)this.A.get(var1);
   }

   @Override
   public Iterator iterator() {
      return this.A.iterator();
   }

   public Iterable wS() {
      return new chr(this);
   }

   public Iterable UT() {
      return new chs(this);
   }

   @Override
   public String toString() {
      return Strings.ff("%s:%d", this.pC, this.A.size());
   }

   class Av implements Iterator {
      int pC;
      int A;

      Av(int var2) {
         this.pC = var2;
         this.A = -1;
         this.pC();
      }

      void pC() {
         this.A++;

         for (; this.A < chq.this.A.size(); this.A++) {
            cht var1 = (cht)chq.this.A.get(this.A);
            if (this.pC == 0) {
               if (var1.E()) {
                  break;
               }
            } else {
               if (this.pC != 1) {
                  throw new RuntimeException();
               }

               if (var1.UT()) {
                  break;
               }
            }
         }
      }

      @Override
      public boolean hasNext() {
         return this.A < chq.this.A.size();
      }

      public cht A() {
         if (this.A > chq.this.A.size()) {
            throw new NoSuchElementException();
         } else {
            cht var1 = (cht)chq.this.A.get(this.A);
            this.pC();
            return var1;
         }
      }
   }
}
