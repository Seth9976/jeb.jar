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
public final class akp extends ajf implements IWildcardType {
   @SerId(1)
   private INativeType ld;
   @SerId(2)
   private int gp;
   @SerId(3)
   int A;
   @SerId(4)
   IWildcardType.Group kS;
   @SerId(5)
   int wS;
   @SerId(6)
   IWildcardType.Group UT;

   akp(akr var1, INativeType var2) {
      super(var1);
      this.ld = var2;
      this.gp = var2.getSize() * 8;
      ((aye)var2).addListener(this);
   }

   @Override
   public INativeType getNativeType() {
      return this.ld;
   }

   @Override
   public void onNativeItemEvent(NativeItemEvent var1) {
      if (var1.getItem() == this.ld) {
         if (var1.getType() == NativeItemEventType.DISPOSED) {
            ((aye)this.ld).removeListener(this);
            this.xC();
         } else {
            this.pC(var1.getType());
         }
      }
   }

   @Override
   protected void A() {
      super.A();
      if (this.ld != null) {
         ((aye)this.ld).removeListener(this);
      }
   }

   akp(akr var1, int var2) {
      super(var1);
      if (var2 <= 0) {
         throw new IllegalArgumentException("Wildcard type has an invalid maximum bitsize: " + this.gp);
      } else {
         this.ld = null;
         this.gp = var2;
      }
   }

   private akp UT() {
      this.sY();
      akp var1 = new akp(this.pC, this.gp);
      var1.A = this.A;
      var1.kS = this.kS;
      if (this.kS == IWildcardType.Group.POINTER) {
         var1.wS = this.wS;
         var1.UT = this.UT;
      }

      return var1;
   }

   @Override
   public int getBitsize() {
      if (this.ld != null) {
         return this.gp;
      } else if (this.A == 0) {
         return this.gp;
      } else {
         Assert.a(this.A >= 1 && this.A <= this.gp, "Illegal effective bitsize");
         return this.A;
      }
   }

   @Override
   public int getMaximumBitsize() {
      return this.ld != null ? this.gp : this.gp;
   }

   @Override
   public int getEffectiveBitsize() {
      return this.ld != null ? this.gp : this.A;
   }

   @Override
   public int getSlotCount() {
      int var1 = this.getBitsize();
      return (var1 + this.pC.A - 1) / this.pC.A;
   }

   @Override
   public boolean isVoid() {
      return this.getBitsize() == 0;
   }

   public int pC(IWildcardType var1) {
      if (var1 == null) {
         throw new NullPointerException();
      } else {
         akp var2 = (akp)var1;
         if (this.ld != null) {
            return var2.ld != null ? 0 : 1;
         } else if (var2.ld != null) {
            return -1;
         } else {
            if (this.A != 0) {
               if (var2.A != 0 && this.A != var2.A) {
                  return 0;
               }
            } else if (var2.A != 0) {
               return -1;
            }

            if (this.A == 0 && var2.A == 0 && this.gp != var2.gp) {
               if (this.gp != 0) {
                  if (var2.gp != 0) {
                     return -(this.gp - var2.gp);
                  }

                  return 1;
               }

               if (var2.gp != 0) {
                  return -1;
               }
            }

            int var3 = this.E();
            int var4 = var2.E();
            return Integer.compare(var3, var4);
         }
      }
   }

   @Override
   public boolean isLessSpecializedThan(IWildcardType var1) {
      if (var1 == null) {
         return false;
      } else {
         int var2 = this.pC(var1);
         return var2 < 0;
      }
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   private int E() {
      byte var1 = 0;
      if (this.kS != null) {
         switch (this.kS) {
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
               if (this.wS != 0) {
                  var1 += 5;
               }

               if (this.UT != null) {
                  var1 += 5;
               }
         }
      }

      if (this.A != 0) {
         var1 += 2;
      }

      return var1;
   }

   @Override
   public boolean isDefined() {
      return this.ld != null;
   }

   @Override
   public boolean isPartiallyDefined() {
      return this.ld == null && (this.A != 0 || this.kS != null);
   }

   @Override
   public boolean isUndefined() {
      return this.ld == null && this.A == 0 && this.kS == null;
   }

   private void sY() {
      if (this.isDefined()) {
         throw new IllegalStateException("The wildcard type is fully defined: " + this);
      }
   }

   @Override
   public boolean isUpdatable() {
      if (this.ld == null) {
         return true;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.ld);
         return var1 instanceof IPrimitiveType && ((IPrimitiveType)var1).getCategory() != null;
      }
   }

   private akp ys() {
      if (this.ld == null) {
         return this.UT();
      } else {
         if (TypeUtil.getNonAlias(this.ld) instanceof IPrimitiveType var2) {
            int var3 = var2.getSize() * 8;
            if (var2.getCategory() == PrimitiveCategory.INTEGER) {
               return this.pC.pC(var3, var3).pC(IWildcardType.Group.INTEGER);
            }

            if (var2.getCategory() == PrimitiveCategory.UNSIGNED) {
               return this.pC.pC(var3, var3).pC(IWildcardType.Group.UINT);
            }

            if (var2.getCategory() == PrimitiveCategory.FLOAT) {
               return this.pC.pC(var3, var3).pC(IWildcardType.Group.FLOAT);
            }
         }

         throw new IllegalStateException("The wildcard type cannot be updated: " + this);
      }
   }

   public akp pC(int var1) {
      if (var1 > this.getBitsize()) {
         throw new IllegalArgumentException();
      } else {
         akp var2 = this.ys();
         var2.A = var1;
         return var2;
      }
   }

   public akp A(int var1) {
      akp var2 = this.ys();
      var2.gp = var1;
      if (var2.A > var2.gp) {
         var2.A = 0;
      }

      return var2;
   }

   @Override
   public IWildcardType.Group getGroup() {
      if (this.ld == null) {
         return this.kS;
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

   public akp pC(IWildcardType.Group var1) {
      if (var1 == IWildcardType.Group.POINTER && this.gp < this.pC.kS) {
         return null;
      } else {
         akp var2 = this.ys();
         var2.kS = var1;
         return var2;
      }
   }

   @Override
   public boolean isFloat() {
      if (this.ld == null) {
         return this.kS == IWildcardType.Group.FLOAT;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.ld);
         return var1 instanceof IPrimitiveType ? ((IPrimitiveType)var1).getCategory() == PrimitiveCategory.FLOAT : false;
      }
   }

   @Override
   public boolean isSigned() {
      if (this.ld == null) {
         return this.kS == IWildcardType.Group.INTEGER;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.ld);
         return var1 instanceof IPrimitiveType ? ((IPrimitiveType)var1).getCategory() == PrimitiveCategory.INTEGER : false;
      }
   }

   @Override
   public boolean isUnsigned() {
      if (this.ld == null) {
         return this.kS == IWildcardType.Group.UINT;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.ld);
         return var1 instanceof IPrimitiveType ? ((IPrimitiveType)var1).getCategory() == PrimitiveCategory.UNSIGNED : false;
      }
   }

   @Override
   public boolean isPointer() {
      if (this.ld == null) {
         return this.kS == IWildcardType.Group.POINTER;
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.ld);
         return var1 instanceof IReferenceType;
      }
   }

   @Override
   public boolean isWildcardPointer() {
      return this.ld == null && this.kS == IWildcardType.Group.POINTER;
   }

   @Override
   public int getPointedBitsize() {
      if (this.ld == null) {
         if (this.kS == IWildcardType.Group.POINTER) {
            return this.wS;
         }
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.ld);
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

   private static IWildcardType.Group pC(INativeType var0) {
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
      if (this.ld == null) {
         if (this.kS == IWildcardType.Group.POINTER) {
            return this.UT;
         }
      } else {
         INativeType var1 = TypeUtil.getNonAlias(this.ld);
         if (var1 instanceof IReferenceType) {
            INativeType var2 = ((IReferenceType)var1).getPointedType();
            return pC(var2);
         }
      }

      return null;
   }

   public akp kS(int var1) {
      akp var2 = this.ys();
      var2.wS = var1;
      var2.kS = IWildcardType.Group.POINTER;
      return var2;
   }

   public akp A(IWildcardType.Group var1) {
      akp var2 = this.ys();
      var2.UT = var1;
      var2.kS = IWildcardType.Group.POINTER;
      return var2;
   }

   public akp kS() {
      return this.pC(false);
   }

   public akp wS() {
      return this.pC(true);
   }

   private akp pC(boolean var1) {
      if (this.isDefined()) {
         return this;
      } else {
         ayy var2 = this.pC.pC;
         int var3 = (this.getBitsize() + 7) / 8;
         IWildcardType.Group var4 = this.kS != null ? this.kS : this.pC.pC();
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
               var6 = this.gp / 8;
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
            }
         }

         if (var5 == null) {
            if (var1) {
               return this;
            } else {
               String var9 = Strings.ff(
                  "Cannot resolve wildcard type with group=%s,bitsize=%d. Make sure to register adequate types to the primitive type manager in order to allow wildcard type resolution and high-level code generation.",
                  var4,
                  this.getBitsize()
               );
               throw new IllegalStateException(var9);
            }
         } else {
            return this.pC.pC((INativeType)var5);
         }
      }
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + this.gp;
      var1 = 31 * var1 + this.A;
      var1 = 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
      var1 = 31 * var1 + (this.ld == null ? 0 : this.ld.hashCode());
      if (this.kS == IWildcardType.Group.POINTER) {
         var1 = 31 * var1 + this.wS;
         var1 = 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
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
         akp var2 = (akp)var1;
         if (this.gp != var2.gp) {
            return false;
         } else if (this.A != var2.A) {
            return false;
         } else if (this.kS != var2.kS) {
            return false;
         } else {
            if (this.ld == null) {
               if (var2.ld != null) {
                  return false;
               }
            } else if (!this.ld.equals(var2.ld)) {
               return false;
            }

            if (this.kS == IWildcardType.Group.POINTER) {
               if (this.wS != var2.wS) {
                  return false;
               }

               if (this.UT != var2.UT) {
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
      if (this.ld != null) {
         return this.ld.getName(true);
      } else {
         String var2 = "?";
         if (this.kS == null) {
            if (!var1) {
               var2 = var2 + "?";
            }

            if (this.A != 0) {
               var2 = var2 + this.A;
            }
         } else if (this.kS == IWildcardType.Group.INTEGER) {
            var2 = var2 + "I";
            if (this.A != 0) {
               var2 = var2 + this.A;
            }
         } else if (this.kS == IWildcardType.Group.UINT) {
            var2 = var2 + "U";
            if (this.A != 0) {
               var2 = var2 + this.A;
            }
         } else if (this.kS == IWildcardType.Group.FLOAT) {
            var2 = var2 + "F";
            if (this.A != 0) {
               var2 = var2 + this.A;
            }
         } else if (this.kS == IWildcardType.Group.POINTER) {
            var2 = var2 + "P";
            if (this.UT != null) {
               if (this.UT == IWildcardType.Group.INTEGER) {
                  var2 = var2 + "I";
               } else if (this.UT == IWildcardType.Group.UINT) {
                  var2 = var2 + "U";
               } else if (this.UT == IWildcardType.Group.FLOAT) {
                  var2 = var2 + "F";
               } else if (this.UT == IWildcardType.Group.POINTER) {
                  var2 = var2 + "P";
               }
            }

            if (this.wS != 0) {
               var2 = var2 + this.wS;
            }
         }

         if (!var1 && this.gp != this.pC.A) {
            var2 = var2 + "/" + this.gp;
         }

         return var2;
      }
   }

   public static IWildcardType pC(IWildcardTypeManager var0, String var1, int var2) {
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

         int var6 = ((akr)var0).A;
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
                  var8 = ((akr)var0).kS;
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
      akp var2 = this;
      if (var1 != null && this.isUpdatable()) {
         if (this.getGroup() == null && var1.getGroup() != null) {
            var2 = this.pC(var1.getGroup());
            if (var1.getGroup() == IWildcardType.Group.POINTER && var1.getPointedBitsize() > 0) {
               var2 = this.kS(var1.getPointedBitsize());
            }
         }

         if (this.getEffectiveBitsize() == 0 && var1.getEffectiveBitsize() > 0) {
            var2 = this.pC(var1.getEffectiveBitsize());
         }
      }

      return var2;
   }

   public static void pC(IEGeneric var0, IEGeneric var1) {
      akp var2 = (akp)var0.getType();
      akp var3 = (akp)var1.getType();
      if (var3 != null) {
         if (var2 == null) {
            var0.setType(var3);
         } else if (var2.kS == null) {
            var2.kS = var3.kS;
            if (var2.kS == IWildcardType.Group.POINTER) {
               if (var2.wS == 0) {
                  var2.wS = var3.wS;
               }

               if (var2.UT == null) {
                  var2.UT = var3.UT;
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
