package com.pnfsoftware.jeb.util.primitives;

import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.List;

public class IntegerRanges {
   private List l = new ArrayList();
   private boolean mergeRanges;

   public IntegerRanges(boolean var1) {
      this.mergeRanges = var1;
   }

   public int count() {
      return this.l.size();
   }

   public void add(int var1, int var2) {
      if (var1 >= var2) {
         throw new RuntimeException();
      } else {
         for (IntegerRanges.IntRange var4 : this.l) {
            if (var1 < var4.end && var2 > var4.beg) {
               throw new RuntimeException();
            }
         }

         int var5;
         for (var5 = 0; var5 < this.l.size(); var5++) {
            IntegerRanges.IntRange var6 = (IntegerRanges.IntRange)this.l.get(var5);
            if (var1 < var6.beg) {
               break;
            }
         }

         if (this.mergeRanges) {
            if (var5 < this.l.size() && var2 == ((IntegerRanges.IntRange)this.l.get(var5)).beg) {
               var2 = ((IntegerRanges.IntRange)this.l.get(var5)).end;
               this.l.remove(var5);
            }

            if (var5 >= 1 && var1 == ((IntegerRanges.IntRange)this.l.get(var5 - 1)).end) {
               var1 = ((IntegerRanges.IntRange)this.l.get(var5 - 1)).beg;
               this.l.remove(var5 - 1);
               var5--;
            }
         }

         this.l.add(var5, new IntegerRanges.IntRange(var1, var2));
      }
   }

   public boolean isInsideRange(int var1, int[] var2) {
      for (IntegerRanges.IntRange var4 : this.l) {
         if (var1 >= var4.beg && var1 < var4.end) {
            if (var2 != null) {
               var2[0] = var4.beg;
               var2[1] = var4.end;
            }

            return true;
         }

         if (var4.beg > var1) {
            break;
         }
      }

      return false;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();

      for (IntegerRanges.IntRange var3 : this.l) {
         Strings.ff(var1, "%d-%d, ", var3.beg, var3.end);
      }

      return var1.toString();
   }

   private static class IntRange {
      int beg;
      int end;

      IntRange(int var1, int var2) {
         this.beg = var1;
         this.end = var2;
      }
   }
}
