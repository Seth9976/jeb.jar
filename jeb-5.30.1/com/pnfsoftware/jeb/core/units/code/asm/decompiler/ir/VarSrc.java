package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Ser
public class VarSrc {
   @SerId(1)
   private VarSrc.E v;
   @SerId(2)
   private VarSrc.E v1;
   @SerId(3)
   private List vX;

   private VarSrc() {
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      var1.append(this.v);
      if (this.v1 != null) {
         var1.append(":").append(this.v1);
         if (this.vX != null) {
            for (VarSrc.E var3 : this.vX) {
               var1.append(":").append(var3);
            }
         }
      }

      return var1.toString();
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.v == null ? 0 : this.v.hashCode());
      var1 = 31 * var1 + (this.v1 == null ? 0 : this.v1.hashCode());
      return 31 * var1 + (this.vX == null ? 0 : this.vX.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         VarSrc var2 = (VarSrc)var1;
         if (this.v == null) {
            if (var2.v != null) {
               return false;
            }
         } else if (!this.v.equals(var2.v)) {
            return false;
         }

         if (this.v1 == null) {
            if (var2.v1 != null) {
               return false;
            }
         } else if (!this.v1.equals(var2.v1)) {
            return false;
         }

         if (this.vX == null) {
            if (var2.vX != null) {
               return false;
            }
         } else if (!this.vX.equals(var2.vX)) {
            return false;
         }

         return true;
      }
   }

   public static VarSrc dup(int var0) {
      VarSrc var1 = new VarSrc();
      var1.v = new VarSrc.E(var0);
      return var1;
   }

   public static VarSrc pair(int var0, int var1) {
      VarSrc var2 = new VarSrc();
      var2.v = new VarSrc.E(var0);
      var2.v1 = new VarSrc.E(var1);
      return var2;
   }

   public static VarSrc slice(int var0, int var1, Integer var2) {
      VarSrc var3 = new VarSrc();
      var3.v = new VarSrc.E(var0, var1, var2);
      return var3;
   }

   public static VarSrc truncated(int var0, int var1) {
      VarSrc var2 = new VarSrc();
      var2.v = new VarSrc.E(var0, 0, var1);
      return var2;
   }

   public List getSource() {
      if (this.v1 == null) {
         return Collections.unmodifiableList(Arrays.asList(this.v));
      } else if (this.vX == null) {
         return Collections.unmodifiableList(Arrays.asList(this.v, this.v1));
      } else {
         throw new RuntimeException("TBI");
      }
   }

   public boolean isDuplicate() {
      return this.v1 == null && this.v.range == null;
   }

   public int getAsDuplicate() {
      return this.v.srcid;
   }

   public boolean isPair() {
      return this.vX == null && this.v1 != null && this.v.range == null && this.v1.range == null;
   }

   public Couple getAsPair() {
      return new Couple(this.v.srcid, this.v1.srcid);
   }

   public boolean isSlice() {
      return this.v1 == null && this.v.range != null;
   }

   public Couple getAsSlice() {
      return new Couple(this.v.srcid, this.v.range);
   }

   public boolean isTruncated() {
      return this.v1 == null && this.v.range != null && (Integer)this.v.range.getFirst() == 0;
   }

   public Couple getAsTruncated() {
      return new Couple(this.v.srcid, (Integer)this.v.range.getSecond());
   }

   public boolean containsSourceId(int var1) {
      ArrayList var2 = new ArrayList();
      this.collectSourceIds(var2, null);
      return var2.contains(var1);
   }

   public void collectSourceIds(Collection var1) {
      this.collectSourceIds(var1, null);
   }

   public List getSourceIds() {
      ArrayList var1 = new ArrayList();
      this.collectSourceIds(var1, null);
      return var1;
   }

   public void collectSourceIds(Collection var1, Collection var2) {
      if (var2 == null) {
         var2 = Collections.emptyList();
      }

      if (!var2.contains(this.v.srcid)) {
         var1.add(this.v.srcid);
      }

      if (this.v1 != null) {
         if (!var2.contains(this.v1.srcid)) {
            var1.add(this.v1.srcid);
         }

         if (this.vX != null) {
            for (VarSrc.E var4 : this.vX) {
               if (!var2.contains(var4.srcid)) {
                  var1.add(var4.srcid);
               }
            }
         }
      }
   }

   @Ser
   public static class E {
      @SerId(1)
      private int srcid;
      @SerId(2)
      private Couple range;

      private E(int var1) {
         this.srcid = var1;
      }

      private E(int var1, int var2, Integer var3) {
         this.srcid = var1;
         if (var2 > 0 || var3 != null) {
            this.range = new Couple(var2, var3);
         }
      }

      @Override
      public String toString() {
         StringBuilder var1 = new StringBuilder();
         var1.append("id_").append(Integer.toHexString(this.srcid));
         if (this.range != null) {
            var1.append("[").append(this.range.getFirst()).append(",").append(this.range.getSecond()).append("[");
         }

         return var1.toString();
      }

      @Override
      public int hashCode() {
         int var1 = 1;
         var1 = 31 * var1 + (this.range == null ? 0 : this.range.hashCode());
         return 31 * var1 + this.srcid;
      }

      @Override
      public boolean equals(Object var1) {
         if (this == var1) {
            return true;
         } else if (var1 == null) {
            return false;
         } else if (this.getClass() != var1.getClass()) {
            return false;
         } else {
            VarSrc.E var2 = (VarSrc.E)var1;
            if (this.range == null) {
               if (var2.range != null) {
                  return false;
               }
            } else if (!this.range.equals(var2.range)) {
               return false;
            }

            return this.srcid == var2.srcid;
         }
      }

      public int getSourceId() {
         return this.srcid;
      }

      public Couple getSourceRange() {
         return this.range;
      }
   }
}
