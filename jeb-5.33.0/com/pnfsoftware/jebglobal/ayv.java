package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventSubType;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IStructureTypeField;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.concurrent.ACLock;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

@Ser
public class ayv extends ayx implements IStructureType {
   @SerId(1)
   private List pC = new ArrayList();
   @SerId(2)
   private int kS;
   @SerId(3)
   private int wS;
   @SerId(4)
   private volatile Integer UT;

   ayv(ayy var1, String var2, String var3, int var4, int var5) {
      super(var1, var2, var3);
      if (var4 == 0) {
         this.kS = 0;
      } else {
         this.kS = Math.max(1, var4);
      }

      this.wS = Math.max(1, var5);
   }

   @Override
   public boolean isStructure() {
      return this.kS != 0;
   }

   @Override
   public boolean isUnion() {
      return this.kS == 0;
   }

   @Override
   public int getPadding() {
      return this.kS;
   }

   @Override
   public int getAlignment() {
      return this.wS;
   }

   public Integer pC(boolean var1) {
      this.gp().verifyLocked();
      if (var1) {
         this.UT = this.getSize();
      } else {
         this.UT = null;
      }

      return this.UT;
   }

   public boolean Ab() {
      return this.UT != null;
   }

   @Override
   public int getSize() {
      if (this.UT != null) {
         return this.UT;
      } else {
         int var1 = this.rl();
         if (this.wS > 1) {
            var1 = (var1 + this.wS - 1) / this.wS * this.wS;
         }

         return Math.max(this.wS, var1);
      }
   }

   public int rl() {
      int var1 = 0;
      if (this.isUnion()) {
         for (ayu var3 : this.pC) {
            if (var3.getSize() > var1) {
               var1 = var3.getSize();
            }
         }
      } else if (!this.pC.isEmpty()) {
         var1 = ((ayu)this.pC.get(this.pC.size() - 1)).getEndOffset();
      }

      return var1;
   }

   protected ayv.Av pC(aye var1, int var2, int var3) {
      INativeType var4 = TypeUtil.getFirstSimpleType(var1);
      int var5 = var4 == null ? 1 : var4.getSize();
      if (var2 != 0) {
         if (!TypeUtil.isBitfieldCompatible(var1)) {
            throw new RuntimeException("Bit field illegal for type: " + var1);
         }

         if (var2 < 0 || var2 > var5 * 8) {
            throw new RuntimeException("Illegal bit field size: " + var2);
         }

         if (var2 == var5 * 8) {
            var2 = 0;
         }
      }

      int var6 = 0;
      int var7 = 0;
      if (!this.pC.isEmpty()) {
         ayu var8 = (ayu)this.pC.get(this.pC.size() - 1);
         if (var8.isBitfield() && var2 != 0 && TypeUtil.areBitfielTypesEquivalent(var8.A, var1)) {
            int var9 = var8.getSize() * 8 - var8.getBitend();
            if (var9 < var2) {
               var6 = var8.getEndOffset();
            } else {
               var6 = var8.getOffset();
               var7 = var8.getBitend();
            }
         } else {
            var6 = var8.getEndOffset();
         }
      }

      INativeType var12 = TypeUtil.getNonAlias(var1);
      int var11;
      if (var12 instanceof ayv) {
         int var10 = ((ayv)var12).getAlignment();
         var11 = Math.min(this.kS, var10);
      } else {
         var11 = Math.min(this.kS, var5);
      }

      if (var3 > 0) {
         var11 = Math.max(var11, var3);
      }

      if (var11 > 1) {
         var6 = (var6 + var11 - 1) / var11 * var11;
      }

      return new ayv.Av(var11, var6, var7, var4);
   }

   public final ayu pC(String var1, INativeType var2, int var3) {
      return this.pC(var1, var2, var3, 0, 0, 0);
   }

   public final ayu pC(String var1, INativeType var2, int var3, int var4, int var5, int var6) {
      return this.pC(var1, var2, var3, var4, var5, var6, true);
   }

   public ayu pC(String var1, INativeType var2, int var3, int var4, int var5, int var6, boolean var7) {
      this.vP();

      ayu var26;
      try (ACLock var8 = this.gp().a()) {
         if (!this.fI()) {
            return null;
         }

         aye var9 = (aye)var2;
         if (this.isCircular(var9)) {
            return null;
         }

         int var10 = 0;
         int var11 = -1;
         int var12 = 0;
         if (this.kS <= 0) {
            if (this.kS != 0) {
               throw new RuntimeException("Neither a structure nor a union");
            }

            if (var3 < 0) {
               var3 = 0;
            } else if (var3 != 0) {
               throw new RuntimeException("Union field is always at offset 0");
            }
         } else {
            ayv.Av var13 = this.pC(var9, var4, var5);
            var12 = var13.pC;
            if (var3 >= 0 && var3 != var13.A) {
               int var14 = Math.min(this.kS, var13.wS.getSize());
               if (var3 % var14 != 0) {
                  return null;
               }

               if (var3 < var13.A) {
                  int var15 = 0;

                  for (ayu var17 : this.pC) {
                     if (var3 < var17.getEndOffset()) {
                        if (var3 >= var17.getOffset()) {
                           return null;
                        }
                        break;
                     }

                     var15++;
                  }

                  int var28 = ((ayu)this.pC.get(var15)).getOffset() - var3;
                  if (var28 < var9.getSize()) {
                     return null;
                  }

                  var11 = var15;
               }
            } else {
               var3 = var13.A;
               var10 = var13.kS;
            }
         }

         if (this.UT != null && var3 >= this.UT) {
            return null;
         }

         var1 = this.pC(var1, var3);
         ayu var24 = new ayu(var1, var9, var3, var10, var4, var5, var6);
         this.pC(var11, var24);
         if (var12 > this.wS) {
            this.wS = var12;
         }

         if (var7) {
            this.pC(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.STRUCT_FIELD_ADDED, var24));
         }

         var26 = var24;
      }

      return var26;
   }

   private void pC(int var1, ayu var2) {
      this.gp().verifyLocked();
      boolean var3 = false;

      for (ayu var5 : this.pC) {
         if (var5.A == var2.A) {
            var3 = true;
            break;
         }
      }

      if (!var3) {
         var2.A.addListener(this);
      }

      if (var1 < 0) {
         this.pC.add(var2);
      } else {
         this.pC.add(var1, var2);
      }
   }

   @Override
   public boolean isCircular(INativeType var1) {
      return TypeUtil.structureInStructure(this, var1);
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public boolean pC(IStructureTypeField var1) {
      ACLock var2 = this.gp().a();

      Throwable var3;
      label98: {
         label105: {
            try {
               if (!this.fI()) {
                  var14 = false;
                  break label105;
               }
            } catch (Throwable var11) {
               var3 = var11;
               if (var2 == null) {
                  throw var11;
               }
               break label98;
            }

            if (var1 == null) {
               boolean var13 = false;
               if (var2 != null) {
                  var2.close();
               }

               return var13;
            }

            boolean var16;
            label101: {
               int var4;
               int var5;
               try {
                  var12 = ((ayu)var1).A;
                  var4 = -1;
                  var5 = 0;

                  for (int var6 = 0; var6 < this.pC.size(); var6++) {
                     ayu var7 = (ayu)this.pC.get(var6);
                     if (var7 == var1) {
                        var4 = var6;
                     } else if (var7.A == var12) {
                        var5++;
                     }
                  }

                  if (var4 < 0) {
                     var16 = false;
                     break label101;
                  }
               } catch (Throwable var10) {
                  var3 = var10;
                  if (var2 == null) {
                     throw var10;
                  }
                  break label98;
               }

               try {
                  if (var5 == 0) {
                     var12.removeListener(this);
                  }

                  this.pC.remove(var4);
                  this.pC(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.STRUCT_FIELD_REMOVED, var1));
                  var16 = true;
               } catch (Throwable var9) {
                  var3 = var9;
                  if (var2 == null) {
                     throw var9;
                  }
                  break label98;
               }

               if (var2 != null) {
                  var2.close();
               }

               return var16;
            }

            if (var2 != null) {
               var2.close();
            }

            return var16;
         }

         if (var2 != null) {
            var2.close();
         }

         return var14;
      }

      try {
         var2.close();
      } catch (Throwable var8) {
         var3.addSuppressed(var8);
      }

      throw var3;
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() instanceof aye) {
         aye var2 = (aye)var1.getItem();
         if (var1.getType().isDeepChange()) {
            this.gp().verifyLocked();
            int var3 = 0;
            int var4 = 0;

            while (var4 < this.pC.size()) {
               if (((ayu)this.pC.get(var4)).A == var2) {
                  ayu var5 = (ayu)this.pC.remove(var4);
                  this.pC(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.STRUCT_FIELD_REMOVED, var5));
                  var3++;
               } else {
                  var4++;
               }
            }

            if (var3 > 0) {
               var2.removeListener(this);
               this.pC(NativeItemEventType.MODIFIED);
            }
         } else {
            this.pC(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public int getFieldsCount() {
      return this.pC.size();
   }

   @Override
   public List getFields() {
      return Collections.unmodifiableList(this.pC);
   }

   @Override
   public int getIndexOfField(IStructureTypeField var1) {
      return this.pC.indexOf(var1);
   }

   @Override
   public List getFieldsWithGaps() {
      if (this.isUnion()) {
         return this.getFields();
      } else {
         ArrayList var1 = new ArrayList();
         int var2 = 0;
         int var3 = 0;

         for (ayu var5 : this.pC) {
            int var6 = var5.getOffset();
            if (var6 != var2 && var6 != var3) {
               var1.add(new ayu(this.pC(null, var3), var6 - var3, var3));
            }

            var1.add(var5);
            var2 = var6;
            var3 = var5.getEndOffset();
         }

         int var7 = this.getSize();
         int var8 = var7 - var3;
         if (var8 > 0) {
            var1.add(new ayu(this.pC(null, var3), var7 - var3, var3));
         }

         return Collections.unmodifiableList(var1);
      }
   }

   public ayu pC(int var1) {
      return var1 >= 0 && var1 < this.pC.size() ? (ayu)this.pC.get(var1) : null;
   }

   public ayu pC(String var1) {
      for (ayu var3 : this.pC) {
         if (var3.pC.equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public ayu A(int var1) {
      for (ayu var3 : this.pC) {
         if (var1 == var3.getOffset()) {
            return var3;
         }
      }

      return null;
   }

   public ayu A(int var1, int var2) {
      for (ayu var4 : this.pC) {
         if (var1 == var4.getOffset() && var2 == var4.getBitstart()) {
            return var4;
         }
      }

      return null;
   }

   public ayu kS(int var1) {
      for (ayu var3 : this.pC) {
         if (var1 >= var3.getOffset() && var1 < var3.getEndOffset()) {
            return var3;
         }
      }

      return null;
   }

   public ayu E(int var1) {
      for (ayu var3 : this.pC) {
         if (var3.getOffset() > var1) {
            return var3;
         }
      }

      return null;
   }

   public final boolean pC(int var1, String var2) {
      ayu var3 = this.pC(var1);
      return var3 == null ? false : this.pC(var3, var2);
   }

   public boolean pC(ayu var1, String var2) {
      this.vP();

      boolean var4;
      try (ACLock var3 = this.gp().a()) {
         if (!this.oT()) {
            return false;
         }

         if (!Strings.equals(var2, var1.pC)) {
            var2 = this.pC(var2, var1.kS);
            if (!var2.equals(var1.pC)) {
               var1.pC = var2;
               this.pC(new NativeItemEvent(NativeItemEventType.UPDATED, this, NativeItemEventSubType.STRUCT_FIELD_RENAMED, var1));
            }
         }

         var4 = true;
      }

      return var4;
   }

   private String pC(String var1, int var2) {
      if (var1 != null && !var1.isEmpty() && !var1.toLowerCase().startsWith("field_")) {
         StringBuilder var3 = new StringBuilder();
         char var4 = var1.charAt(0);
         var3.append(Character.isJavaIdentifierStart(var4) ? var4 : '_');

         for (int var5 = 1; var5 < var1.length(); var5++) {
            var4 = var1.charAt(var5);
            var3.append(Character.isJavaIdentifierPart(var4) ? var4 : '_');
         }

         var1 = var3.toString();

         for (int var8 = 1; this.pC(var1) != null; var8++) {
            var1 = var3.toString() + "_" + var8;
         }

         return var1;
      } else {
         return Strings.ff("field_%X", var2);
      }
   }

   @Override
   protected void A() {
      super.A();
      HashSet var1 = new HashSet();

      for (ayu var3 : this.pC) {
         var1.add(var3.A);
      }

      for (aye var5 : var1) {
         var5.removeListener(this);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%sType(%s,%d)", this.isUnion() ? "Union" : "Struct", this.getSignature(false), this.getSize());
   }

   private static class Av {
      int pC;
      int A;
      int kS;
      INativeType wS;

      Av(int var1, int var2, int var3, INativeType var4) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
      }
   }
}
