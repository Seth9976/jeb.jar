package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEvent;
import com.pnfsoftware.jeb.core.units.code.asm.items.NativeItemEventType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrimitiveType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IReferenceType;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrimitiveCategory;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeLayoutInfo;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;

@Ser
public final class ams extends ali implements IWildcardType {
   @SerId(1)
   private INativeType zz;
   @SerId(2)
   private int JY;
   @SerId(3)
   int RF;
   @SerId(4)
   IWildcardType.Group xK;
   @SerId(5)
   int Dw;
   @SerId(6)
   IWildcardType.Group Uv;

   ams(amu var1, INativeType var2) {
      super(var1);
      this.zz = var2;
      this.JY = var2.getSize() * 8;
      ((bbd)var2).addListener(this);
   }

   @Override
   public INativeType getNativeType() {
      return this.zz;
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() == this.zz) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            ((bbd)this.zz).removeListener(this);
            this.PV();
         } else {
            this.q(var1.getType());
         }
      }
   }

   @Override
   protected void RF() {
      super.RF();
      if (this.zz != null) {
         ((bbd)this.zz).removeListener(this);
      }
   }

   ams(amu var1, int var2) {
      super(var1);
      if (var2 <= 0) {
         throw new IllegalArgumentException("Wildcard type has an invalid maximum bitsize: " + this.JY);
      } else {
         this.zz = null;
         this.JY = var2;
      }
   }

   private ams Uv() {
      this.gO();
      ams var1 = new ams(this.q, this.JY);
      var1.RF = this.RF;
      var1.xK = this.xK;
      if (this.xK == IWildcardType.Group.POINTER) {
         var1.Dw = this.Dw;
         var1.Uv = this.Uv;
      }

      return var1;
   }

   @Override
   public int getBitsize() {
      if (this.zz != null) {
         return this.JY;
      } else if (this.RF == 0) {
         return this.JY;
      } else {
         Assert.a(this.RF >= 1 && this.RF <= this.JY, "Illegal effective bitsize");
         return this.RF;
      }
   }

   @Override
   public int getMaximumBitsize() {
      return this.zz != null ? this.JY : this.JY;
   }

   @Override
   public int getEffectiveBitsize() {
      return this.zz != null ? this.JY : this.RF;
   }

   @Override
   public int getSlotCount() {
      int var1 = this.getBitsize();
      return (var1 + this.q.RF - 1) / this.q.RF;
   }

   @Override
   public boolean isVoid() {
      return this.getBitsize() == 0;
   }

   public int q(IWildcardType var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         ams var2 = (ams)var1;
         if (this.zz != null) {
            return var2.zz != null ? 0 : 1;
         } else if (var2.zz != null) {
            return -1;
         } else {
            if (this.RF != 0) {
               if (var2.RF != 0 && this.RF != var2.RF) {
                  return 0;
               }
            } else if (var2.RF != 0) {
               return -1;
            }

            if (this.RF == 0 && var2.RF == 0 && this.JY != var2.JY) {
               if (this.JY != 0) {
                  if (var2.JY != 0) {
                     return -(this.JY - var2.JY);
                  }

                  return 1;
               }

               if (var2.JY != 0) {
                  return -1;
               }
            }

            int var3 = this.oW();
            int var4 = var2.oW();
            return Integer.compare(var3, var4);
         }
      }
   }

   @Override
   public boolean isLessSpecializedThan(IWildcardType var1) {
      int var2 = this.q(var1);
      return var2 < 0;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private int oW() {
      byte var1 = 0;
      if (this.xK != null) {
         switch (this.xK) {
            case INTEGER:
               var1 += 10;
               break;
            case UINT:
               var1 += 12;
               break;
            case FLOAT:
               var1 += 20;
               break;
            case POINTER:
               var1 += 30;
               if (this.Dw != 0) {
                  var1 += 5;
               }

               if (this.Uv != null) {
                  var1 += 5;
               }
         }
      }

      if (this.RF != 0) {
         var1 += 2;
      }

      return var1;
   }

   @Override
   public boolean isDefined() {
      return this.zz != null;
   }

   @Override
   public boolean isPartiallyDefined() {
      return this.zz == null && (this.RF != 0 || this.xK != null);
   }

   @Override
   public boolean isUndefined() {
      return this.zz == null && this.RF == 0 && this.xK == null;
   }

   private void gO() {
      if (this.isDefined()) {
         throw new IllegalStateException("The wildcard type is fully defined: " + this);
      }
   }

   @Override
   public boolean isUpdatable() {
      if (this.zz == null) {
         return true;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.zz);
         return var1 instanceof IPrimitiveType && ((IPrimitiveType)var1).getCategory() != null;
      }
   }

   private ams nf() {
      if (this.zz == null) {
         return this.Uv();
      } else {
         if (TypeUtil.getNonAlias(this.zz) instanceof IPrimitiveType var2) {
            int var3 = var2.getSize() * 8;
            if (var2.getCategory() == PrimitiveCategory.INTEGER) {
               return this.q.q(var3, var3).q(IWildcardType.Group.INTEGER);
            }

            if (var2.getCategory() == PrimitiveCategory.UNSIGNED) {
               return this.q.q(var3, var3).q(IWildcardType.Group.UINT);
            }

            if (var2.getCategory() == PrimitiveCategory.FLOAT) {
               return this.q.q(var3, var3).q(IWildcardType.Group.FLOAT);
            }
         }

         throw new IllegalStateException("The wildcard type cannot be updated: " + this);
      }
   }

   public ams q(int var1) {
      if (var1 > this.getBitsize()) {
         throw new IllegalArgumentException();
      } else {
         ams var2 = this.nf();
         var2.RF = var1;
         return var2;
      }
   }

   public ams RF(int var1) {
      ams var2 = this.nf();
      var2.JY = var1;
      if (var2.RF > var2.JY) {
         var2.RF = 0;
      }

      return var2;
   }

   @Override
   public IWildcardType.Group getGroup() {
      if (this.zz == null) {
         return this.xK;
      } else if (this.isFloat()) {
         return IWildcardType.Group.FLOAT;
      } else if (this.isPointer()) {
         return IWildcardType.Group.POINTER;
      } else if (this.isSigned()) {
         return IWildcardType.Group.INTEGER;
      } else {
         return this.isUnsigned() ? IWildcardType.Group.UINT : null;
      }
   }

   public ams q(IWildcardType.Group var1) {
      ams var2 = this.nf();
      var2.xK = var1;
      return var2;
   }

   @Override
   public boolean isFloat() {
      if (this.zz == null) {
         return this.xK == IWildcardType.Group.FLOAT;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.zz);
         return var1 instanceof IPrimitiveType ? ((IPrimitiveType)var1).getCategory() == PrimitiveCategory.FLOAT : false;
      }
   }

   @Override
   public boolean isSigned() {
      if (this.zz == null) {
         return this.xK == IWildcardType.Group.INTEGER;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.zz);
         return var1 instanceof IPrimitiveType ? ((IPrimitiveType)var1).getCategory() == PrimitiveCategory.INTEGER : false;
      }
   }

   @Override
   public boolean isUnsigned() {
      if (this.zz == null) {
         return this.xK == IWildcardType.Group.UINT;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.zz);
         return var1 instanceof IPrimitiveType ? ((IPrimitiveType)var1).getCategory() == PrimitiveCategory.UNSIGNED : false;
      }
   }

   @Override
   public boolean isPointer() {
      if (this.zz == null) {
         return this.xK == IWildcardType.Group.POINTER;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.zz);
         return var1 instanceof IReferenceType;
      }
   }

   @Override
   public boolean isWildcardPointer() {
      return this.zz == null && this.xK == IWildcardType.Group.POINTER;
   }

   @Override
   public int getPointedBitsize() {
      if (this.zz == null) {
         if (this.xK == IWildcardType.Group.POINTER) {
            return this.Dw;
         }
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.zz);
         if (var1 instanceof IReferenceType) {
            INativeType var2 = ((IReferenceType)var1).getPointedType();
            INativeType var3 = TypeUtil.getFirstSimpleType(var2);
            if (var3 != null) {
               return var3.getSize() * 8;
            }
         }
      }

      return 0;
   }

   @Override
   public int getPointedSize() {
      int var1 = this.getPointedBitsize();
      return var1 == 0 ? 0 : (var1 + 7) / 8;
   }

   private static IWildcardType.Group q(INativeType var0) {
      INativeType var1 = TypeUtil.getNonAlias(var0);
      if (var1 instanceof IPrimitiveType) {
         PrimitiveCategory var2 = ((IPrimitiveType)var1).getCategory();
         if (var2 == PrimitiveCategory.INTEGER) {
            return IWildcardType.Group.INTEGER;
         }

         if (var2 == PrimitiveCategory.UNSIGNED) {
            return IWildcardType.Group.UINT;
         }

         if (var2 == PrimitiveCategory.FLOAT) {
            return IWildcardType.Group.FLOAT;
         }
      }

      return null;
   }

   @Override
   public IWildcardType.Group getPointedGroup() {
      if (this.zz == null) {
         if (this.xK == IWildcardType.Group.POINTER) {
            return this.Uv;
         }
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.zz);
         if (var1 instanceof IReferenceType) {
            INativeType var2 = ((IReferenceType)var1).getPointedType();
            return q(var2);
         }
      }

      return null;
   }

   public ams xK(int var1) {
      ams var2 = this.nf();
      var2.Dw = var1;
      var2.xK = IWildcardType.Group.POINTER;
      return var2;
   }

   public ams RF(IWildcardType.Group var1) {
      ams var2 = this.nf();
      var2.Uv = var1;
      var2.xK = IWildcardType.Group.POINTER;
      return var2;
   }

   public ams xK() {
      return this.q(false);
   }

   public ams Dw() {
      return this.q(true);
   }

   private ams q(boolean var1) {
      if (this.isDefined()) {
         return this;
      } else {
         bby var2 = this.q.q;
         int var3 = (this.getBitsize() + 7) / 8;
         IWildcardType.Group var4 = this.xK != null ? this.xK : this.q.q();
         Object var5;
         if (var4 == IWildcardType.Group.INTEGER) {
            var5 = var2.getInteger(var3, true);
         } else if (var4 == IWildcardType.Group.UINT) {
            var5 = var2.getInteger(var3, false);
         } else if (var4 == IWildcardType.Group.FLOAT) {
            var5 = var2.getExactFloat(var3);
         } else {
            if (var4 != IWildcardType.Group.POINTER) {
               throw new RuntimeException("Unsupported group: " + var4);
            }

            int var6 = this.getPointedSize();
            if (var6 == 0) {
               var6 = this.JY / 8;
            }

            IWildcardType.Group var8 = this.getPointedGroup();
            INativeType var7;
            if (var8 == null || var8 == IWildcardType.Group.INTEGER) {
               var7 = var2.getInteger(var6, true);
            } else if (var8 == IWildcardType.Group.FLOAT) {
               var7 = var2.getExactFloat(var6);
            } else {
               var7 = var2.getInteger(var6, false);
            }

            if (var7 != null) {
               var5 = var2.createReference(var7);
            } else {
               var5 = var2.getVoidReference();
               RuntimeException var9 = new RuntimeException(Strings.ff("Cannot resolve wildcard: %s (falling back to PVOID)", this));
               aeb.q(var9);
            }
         }

         if (var5 == null) {
            if (var1) {
               return this;
            } else {
               String var10 = Strings.ff(
                  "Cannot resolve wildcard type with group=%s,bitsize=%d. Make sure to register adequate types to the primitive type manager in order to allow wildcard type resolution and high-level code generation.",
                  var4,
                  this.getBitsize()
               );
               throw new IllegalStateException(var10);
            }
         } else {
            return this.q.q((INativeType)var5);
         }
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.JY;
      var1 = 31 * var1 + this.RF;
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.zz == null ? 0 : this.zz.hashCode());
      if (this.xK == IWildcardType.Group.POINTER) {
         var1 = 31 * var1 + this.Dw;
         var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      }

      return var1;
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
         ams var2 = (ams)var1;
         if (this.JY != var2.JY) {
            return false;
         } else if (this.RF != var2.RF) {
            return false;
         } else if (this.xK != var2.xK) {
            return false;
         } else {
            if (this.zz == null) {
               if (var2.zz != null) {
                  return false;
               }
            } else if (!this.zz.equals(var2.zz)) {
               return false;
            }

            if (this.xK == IWildcardType.Group.POINTER) {
               if (this.Dw != var2.Dw) {
                  return false;
               }

               if (this.Uv != var2.Uv) {
                  return false;
               }
            }

            return true;
         }
      }
   }

   @Override
   public boolean isEquivalent(IWildcardType var1) {
      if (this.equals(var1)) {
         return true;
      } else {
         if (var1 != null && this.isResolved() && var1.isResolved()) {
            INativeType var2 = this.getNativeType();
            INativeType var3 = var1.getNativeType();
            INativeType var4 = TypeUtil.getNonAlias(var2);
            INativeType var5 = TypeUtil.getNonAlias(var3);
            if (var4 != var2 || var5 != var3) {
               return var4.equals(var5);
            }
         }

         return false;
      }
   }

   @Override
   public String toString() {
      return this.toString(true);
   }

   @Override
   public String toString(boolean var1) {
      if (this.zz != null) {
         return this.zz.getName(true);
      } else {
         String var2 = "?";
         if (this.xK == null) {
            if (!var1) {
               var2 = var2 + "?";
            }

            if (this.RF != 0) {
               var2 = var2 + this.RF;
            }
         } else if (this.xK == IWildcardType.Group.INTEGER) {
            var2 = var2 + "I";
            if (this.RF != 0) {
               var2 = var2 + this.RF;
            }
         } else if (this.xK == IWildcardType.Group.UINT) {
            var2 = var2 + "U";
            if (this.RF != 0) {
               var2 = var2 + this.RF;
            }
         } else if (this.xK == IWildcardType.Group.FLOAT) {
            var2 = var2 + "F";
            if (this.RF != 0) {
               var2 = var2 + this.RF;
            }
         } else if (this.xK == IWildcardType.Group.POINTER) {
            var2 = var2 + "P";
            if (this.Uv != null) {
               if (this.Uv == IWildcardType.Group.INTEGER) {
                  var2 = var2 + "I";
               } else if (this.Uv == IWildcardType.Group.UINT) {
                  var2 = var2 + "U";
               } else if (this.Uv == IWildcardType.Group.FLOAT) {
                  var2 = var2 + "F";
               } else if (this.Uv == IWildcardType.Group.POINTER) {
                  var2 = var2 + "P";
               }
            }

            if (this.Dw != 0) {
               var2 = var2 + this.Dw;
            }
         }

         if (!var1 && this.JY != this.q.RF) {
            var2 = var2 + "/" + this.JY;
         }

         return var2;
      }
   }

   public static IWildcardType q(IWildcardTypeManager var0, String var1) {
      return q(var0, var1, 0);
   }

   public static IWildcardType q(IWildcardTypeManager var0, String var1, int var2) {
      if (!var1.startsWith("?")) {
         return var0.create(var1);
      } else if (var1.equals("?")) {
         return var2 > 0 ? var0.createWithMaximumBitsize(var2) : var0.createWithSlotcount(1);
      } else {
         int var3 = 1;
         char var4 = Character.toUpperCase(var1.charAt(var3++));
         IWildcardType.Group var5;
         if (var4 == '?') {
            var5 = null;
         } else if (var4 == 'I') {
            var5 = IWildcardType.Group.INTEGER;
         } else if (var4 == 'U') {
            var5 = IWildcardType.Group.UINT;
         } else if (var4 == 'F') {
            var5 = IWildcardType.Group.FLOAT;
         } else if (var4 == 'P') {
            var5 = IWildcardType.Group.POINTER;
         } else {
            if (!Character.isDigit(var4)) {
               throw new IllegalArgumentException();
            }

            var5 = null;
            var3--;
         }

         int var6 = ((amu)var0).RF;
         int var7 = var2 > 0 ? var2 : var6;
         int var8 = 0;
         int var9 = 0;
         IWildcardType.Group var10 = null;
         if (var5 == IWildcardType.Group.POINTER && var3 < var1.length()) {
            var4 = var1.charAt(var3);
            if (var4 == 'I') {
               var10 = IWildcardType.Group.INTEGER;
               var3++;
            } else if (var4 == 'U') {
               var10 = IWildcardType.Group.UINT;
               var3++;
            } else if (var4 == 'F') {
               var10 = IWildcardType.Group.FLOAT;
               var3++;
            } else if (var4 == 'P') {
               var10 = IWildcardType.Group.POINTER;
               var3++;
            }
         }

         if (var3 < var1.length()) {
            var4 = var1.charAt(var3++);
            if (Character.isDigit(var4)) {
               int var11 = var1.indexOf(47, --var3);
               if (var11 < 0) {
                  var8 = Integer.parseInt(var1.substring(var3));
                  var3 = var1.length();
               } else {
                  var8 = Integer.parseInt(var1.substring(var3, var11));
                  var4 = var1.charAt(var11);
                  var3 = var11 + 1;
               }

               if (var5 == IWildcardType.Group.POINTER) {
                  var9 = var8;
                  var8 = ((amu)var0).xK;
                  var7 = var8;
               }
            }

            if (var4 == '/') {
               var7 = Integer.parseInt(var1.substring(var3));
               var3 = var1.length();
            } else if (var2 == 0 && var8 > var6) {
               var7 = (var8 + var6 - 1) / var6 * var6;
            }
         }

         if (var3 < var1.length()) {
            throw new IllegalArgumentException();
         } else {
            IWildcardType var16 = var0.createWithBitsizes(var7, var8);
            if (var5 != null) {
               var16 = var16.updateGroup(var5);
               if (var5 == IWildcardType.Group.POINTER) {
                  var16 = var16.updatePointedBitsize(var9);
                  var16 = var16.updatePointedGroup(var10);
               }
            }

            return var16;
         }
      }
   }

   @Override
   public IWildcardType updateProperties(IWildcardType var1) {
      ams var2 = this;
      if (var1 != null && this.isUpdatable()) {
         if (this.getGroup() == null && var1.getGroup() != null) {
            var2 = this.q(var1.getGroup());
            if (var1.getGroup() == IWildcardType.Group.POINTER && var1.getPointedBitsize() > 0) {
               var2 = this.xK(var1.getPointedBitsize());
            }
         }

         if (this.getEffectiveBitsize() == 0 && var1.getEffectiveBitsize() > 0) {
            var2 = this.q(var1.getEffectiveBitsize());
         }
      }

      return var2;
   }

   public static void q(IEGeneric var0, IEGeneric var1) {
      ams var2 = (ams)var0.getType();
      ams var3 = (ams)var1.getType();
      if (var3 != null) {
         if (var2 == null) {
            var0.setType(var3);
         } else if (var2.xK == null) {
            var2.xK = var3.xK;
            if (var2.xK == IWildcardType.Group.POINTER) {
               if (var2.Dw == 0) {
                  var2.Dw = var3.Dw;
               }

               if (var2.Uv == null) {
                  var2.Uv = var3.Uv;
               }
            }
         }
      }
   }

   @Override
   public TypeLayoutInfo getLayoutInfo() {
      int var1 = this.getSlotCount();
      if (this.isInteger()) {
         return TypeLayoutInfo.i(var1);
      } else if (this.isFloat()) {
         return TypeLayoutInfo.f(var1);
      } else {
         return this.isPointer() ? TypeLayoutInfo.p(var1) : TypeLayoutInfo.c(var1);
      }
   }
}
