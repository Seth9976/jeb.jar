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
public class bbv extends bbx implements IStructureType {
   @SerId(1)
   private List q = new ArrayList();
   @SerId(2)
   private int xK;
   @SerId(3)
   private int Dw;
   @SerId(4)
   private volatile Integer Uv;

   bbv(bby var1, String var2, String var3, int var4, int var5) {
      super(var1, var2, var3);
      if (var4 == 0) {
         this.xK = 0;
      } else {
         this.xK = Math.max(1, var4);
      }

      this.Dw = Math.max(1, var5);
   }

   @Override
   public boolean isStructure() {
      return this.xK != 0;
   }

   @Override
   public boolean isUnion() {
      return this.xK == 0;
   }

   @Override
   public int getPadding() {
      return this.xK;
   }

   @Override
   public int getAlignment() {
      return this.Dw;
   }

   public Integer q(boolean var1) {
      this.za().verifyLocked();
      if (var1) {
         this.Uv = this.getSize();
      } else {
         this.Uv = null;
      }

      return this.Uv;
   }

   public boolean If() {
      return this.Uv != null;
   }

   @Override
   public int getSize() {
      if (this.Uv != null) {
         return this.Uv;
      } else {
         int var1 = this.Dz();
         if (this.Dw > 1) {
            var1 = (var1 + this.Dw - 1) / this.Dw * this.Dw;
         }

         return Math.max(this.Dw, var1);
      }
   }

   public int Dz() {
      int var1 = 0;
      if (this.isUnion()) {
         for (bbu var3 : this.q) {
            if (var3.getSize() > var1) {
               var1 = var3.getSize();
            }
         }
      } else if (!this.q.isEmpty()) {
         var1 = ((bbu)this.q.get(this.q.size() - 1)).getEndOffset();
      }

      return var1;
   }

   protected bbv.eo q(bbd var1, int var2, int var3) {
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
      if (!this.q.isEmpty()) {
         bbu var8 = (bbu)this.q.get(this.q.size() - 1);
         if (var8.isBitfield() && var2 != 0 && TypeUtil.areBitfielTypesEquivalent(var8.RF, var1)) {
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
      if (var12 instanceof bbv) {
         int var10 = ((bbv)var12).getAlignment();
         var11 = Math.min(this.xK, var10);
      } else {
         var11 = Math.min(this.xK, var5);
      }

      if (var3 > 0) {
         var11 = Math.max(var11, var3);
      }

      if (var11 > 1) {
         var6 = (var6 + var11 - 1) / var11 * var11;
      }

      return new bbv.eo(var11, var6, var7, var4);
   }

   public final bbu q(String var1, INativeType var2, int var3) {
      return this.q(var1, var2, var3, 0, 0, 0);
   }

   public final bbu q(String var1, INativeType var2, int var3, int var4, int var5, int var6) {
      return this.q(var1, var2, var3, var4, var5, var6, true);
   }

   public bbu q(String var1, INativeType var2, int var3, int var4, int var5, int var6, boolean var7) {
      this.Me();

      bbu var26;
      try (ACLock var8 = this.za().a()) {
         if (!this.HF()) {
            return null;
         }

         bbd var9 = (bbd)var2;
         if (this.isCircular(var9)) {
            return null;
         }

         int var10 = 0;
         int var11 = -1;
         int var12 = 0;
         if (this.xK <= 0) {
            if (this.xK != 0) {
               throw new RuntimeException("Neither a structure nor a union");
            }

            if (var3 < 0) {
               var3 = 0;
            } else if (var3 != 0) {
               throw new RuntimeException("Union field is always at offset 0");
            }
         } else {
            bbv.eo var13 = this.q(var9, var4, var5);
            var12 = var13.q;
            if (var3 >= 0 && var3 != var13.RF) {
               int var14 = Math.min(this.xK, var13.Dw.getSize());
               if (var3 % var14 != 0) {
                  return null;
               }

               if (var3 < var13.RF) {
                  int var15 = 0;

                  for (bbu var17 : this.q) {
                     if (var3 < var17.getEndOffset()) {
                        if (var3 >= var17.getOffset()) {
                           return null;
                        }
                        break;
                     }

                     var15++;
                  }

                  int var28 = ((bbu)this.q.get(var15)).getOffset() - var3;
                  if (var28 < var9.getSize()) {
                     return null;
                  }

                  var11 = var15;
               }
            } else {
               var3 = var13.RF;
               var10 = var13.xK;
            }
         }

         if (this.Uv != null && var3 >= this.Uv) {
            return null;
         }

         var1 = this.q(var1, var3);
         bbu var24 = new bbu(var1, var9, var3, var10, var4, var5, var6);
         this.q(var11, var24);
         if (var12 > this.Dw) {
            this.Dw = var12;
         }

         if (var7) {
            this.q(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.STRUCT_FIELD_ADDED, var24));
         }

         var26 = var24;
      }

      return var26;
   }

   private void q(int var1, bbu var2) {
      this.za().verifyLocked();
      boolean var3 = false;

      for (bbu var5 : this.q) {
         if (var5.RF == var2.RF) {
            var3 = true;
            break;
         }
      }

      if (!var3) {
         var2.RF.addListener(this);
      }

      if (var1 < 0) {
         this.q.add(var2);
      } else {
         this.q.add(var1, var2);
      }
   }

   @Override
   public boolean isCircular(INativeType var1) {
      return TypeUtil.structureInStructure(this, var1);
   }

   public void mI() {
      while (!this.q.isEmpty()) {
         this.RF(0);
      }
   }

   public final boolean RF(int var1) {
      bbu var2 = this.xK(var1);
      return var2 == null ? false : this.q(var2);
   }

   // $VF: Duplicated exception handlers to handle obfuscated exceptions
   public boolean q(IStructureTypeField var1) {
      ACLock var2 = this.za().a();

      Throwable var3;
      label98: {
         label105: {
            try {
               if (!this.HF()) {
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
                  var12 = ((bbu)var1).RF;
                  var4 = -1;
                  var5 = 0;

                  for (int var6 = 0; var6 < this.q.size(); var6++) {
                     bbu var7 = (bbu)this.q.get(var6);
                     if (var7 == var1) {
                        var4 = var6;
                     } else if (var7.RF == var12) {
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

                  this.q.remove(var4);
                  this.q(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.STRUCT_FIELD_REMOVED, var1));
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
      if (var1.getItem() instanceof bbd) {
         bbd var2 = (bbd)var1.getItem();
         if (var1.getType().isDeepChange()) {
            this.za().verifyLocked();
            int var3 = 0;
            int var4 = 0;

            while (var4 < this.q.size()) {
               if (((bbu)this.q.get(var4)).RF == var2) {
                  bbu var5 = (bbu)this.q.remove(var4);
                  this.q(new NativeItemEvent(NativeItemEventType.MODIFIED, this, NativeItemEventSubType.STRUCT_FIELD_REMOVED, var5));
                  var3++;
               } else {
                  var4++;
               }
            }

            if (var3 > 0) {
               var2.removeListener(this);
               this.q(NativeItemEventType.MODIFIED);
            }
         } else {
            this.q(NativeItemEventType.UPDATED);
         }
      }
   }

   @Override
   public int getFieldsCount() {
      return this.q.size();
   }

   @Override
   public List getFields() {
      return Collections.unmodifiableList(this.q);
   }

   @Override
   public int getIndexOfField(IStructureTypeField var1) {
      return this.q.indexOf(var1);
   }

   @Override
   public List getFieldsWithGaps() {
      if (this.isUnion()) {
         return this.getFields();
      } else {
         ArrayList var1 = new ArrayList();
         int var2 = 0;
         int var3 = 0;

         for (bbu var5 : this.q) {
            int var6 = var5.getOffset();
            if (var6 != var2 && var6 != var3) {
               var1.add(new bbu(this.q(null, var3), var6 - var3, var3));
            }

            var1.add(var5);
            var2 = var6;
            var3 = var5.getEndOffset();
         }

         int var7 = this.getSize();
         int var8 = var7 - var3;
         if (var8 > 0) {
            var1.add(new bbu(this.q(null, var3), var7 - var3, var3));
         }

         return Collections.unmodifiableList(var1);
      }
   }

   public bbu xK(int var1) {
      return var1 >= 0 && var1 < this.q.size() ? (bbu)this.q.get(var1) : null;
   }

   public bbu q(String var1) {
      for (bbu var3 : this.q) {
         if (var3.q.equals(var1)) {
            return var3;
         }
      }

      return null;
   }

   public bbu oW(int var1) {
      for (bbu var3 : this.q) {
         if (var1 == var3.getOffset()) {
            return var3;
         }
      }

      return null;
   }

   public bbu RF(int var1, int var2) {
      for (bbu var4 : this.q) {
         if (var1 == var4.getOffset() && var2 == var4.getBitstart()) {
            return var4;
         }
      }

      return null;
   }

   public bbu gO(int var1) {
      for (bbu var3 : this.q) {
         if (var1 >= var3.getOffset() && var1 < var3.getEndOffset()) {
            return var3;
         }
      }

      return null;
   }

   public bbu nf(int var1) {
      for (bbu var3 : this.q) {
         if (var3.getOffset() > var1) {
            return var3;
         }
      }

      return null;
   }

   public final boolean q(int var1, String var2) {
      bbu var3 = this.xK(var1);
      return var3 == null ? false : this.q(var3, var2);
   }

   public boolean q(bbu var1, String var2) {
      this.Me();

      boolean var4;
      try (ACLock var3 = this.za().a()) {
         if (!this.JY()) {
            return false;
         }

         if (!Strings.equals(var2, var1.q)) {
            var2 = this.q(var2, var1.xK);
            if (!var2.equals(var1.q)) {
               var1.q = var2;
               this.q(new NativeItemEvent(NativeItemEventType.UPDATED, this, NativeItemEventSubType.STRUCT_FIELD_RENAMED, var1));
            }
         }

         var4 = true;
      }

      return var4;
   }

   public boolean q(bbu var1, bbd var2) {
      this.Me();

      boolean var4;
      try (ACLock var3 = this.za().a()) {
         if (!this.JY()) {
            return false;
         }

         if (var2 != var1.RF && var2.getSize() == var1.RF.getSize()) {
            var1.RF = var2;
         }

         var4 = true;
      }

      return var4;
   }

   private String q(String var1, int var2) {
      if (var1 != null && !var1.isEmpty() && !var1.toLowerCase().startsWith("field_")) {
         StringBuilder var3 = new StringBuilder();
         char var4 = var1.charAt(0);
         var3.append(Character.isJavaIdentifierStart(var4) ? var4 : '_');

         for (int var5 = 1; var5 < var1.length(); var5++) {
            var4 = var1.charAt(var5);
            var3.append(Character.isJavaIdentifierPart(var4) ? var4 : '_');
         }

         var1 = var3.toString();

         for (int var8 = 1; this.q(var1) != null; var8++) {
            var1 = var3.toString() + "_" + var8;
         }

         return var1;
      } else {
         return Strings.ff("field_%X", var2);
      }
   }

   @Override
   protected void RF() {
      super.RF();
      HashSet var1 = new HashSet();

      for (bbu var3 : this.q) {
         var1.add(var3.RF);
      }

      for (bbd var5 : var1) {
         var5.removeListener(this);
      }
   }

   @Override
   public String toString() {
      return Strings.ff("%sType(%s,%d)", this.isUnion() ? "Union" : "Struct", this.getSignature(false), this.getSize());
   }

   private static class eo {
      int q;
      int RF;
      int xK;
      INativeType Dw;

      eo(int var1, int var2, int var3, INativeType var4) {
         this.q = var1;
         this.RF = var2;
         this.xK = var3;
         this.Dw = var4;
      }
   }
}
