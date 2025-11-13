package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ContextAccessType;
import com.pnfsoftware.jeb.core.units.code.android.IDexContextInfoProvider;
import com.pnfsoftware.jeb.core.units.code.android.JvmMethodSig;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.DInvokeType;
import com.pnfsoftware.jeb.core.units.code.android.ir.DTypeInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.DexDecEvaluationException;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDCallInfo;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDEmuFrame;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDImm;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDIndex;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDState;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDStaticField;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDVar;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.IJavaTypeFactory;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class bpp extends bph implements IDCallInfo {
   private static final ILogger E = GlobalLog.getLogger(bpp.class);
   DInvokeType A;
   String kS;
   IDIndex wS;
   IDExpression[] UT;
   private String sY;

   bpp(IDIndex var1, IDExpression[] var2, IJavaType var3, String var4, DInvokeType var5) {
      super(var3);
      if (var5 != null && var1 != null && var4 != null) {
         if (var2 == null) {
            var2 = new IDExpression[0];
         }

         this.wS = var1;
         this.UT = var2;
         this.kS = var4;
         this.A = var5;
         int var6 = var4.indexOf("->");
         if (var6 < 0) {
            throw new IllegalArgumentException();
         } else {
            var6 += 2;
            int var7 = var4.indexOf("(", var6);
            if (var7 < 0) {
               throw new IllegalArgumentException();
            } else {
               this.sY = var4.substring(var6, var7);
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + Arrays.hashCode((Object[])this.UT);
      var1 = 31 * var1 + (this.A == null ? 0 : this.A.hashCode());
      var1 = 31 * var1 + (this.wS == null ? 0 : this.wS.hashCode());
      return 31 * var1 + (this.kS == null ? 0 : this.kS.hashCode());
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (!super.equalsEx(var1, var2)) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bpp var3 = (bpp)var1;
         if (!pC(this.UT, var3.UT, var2)) {
            return false;
         } else if (this.A != var3.A) {
            return false;
         } else {
            if (this.wS == null) {
               if (var3.wS != null) {
                  return false;
               }
            } else if (!this.wS.equalsEx(var3.wS, var2)) {
               return false;
            }

            if (this.kS == null) {
               if (var3.kS != null) {
                  return false;
               }
            } else if (!this.kS.equals(var3.kS)) {
               return false;
            }

            return true;
         }
      }
   }

   @Override
   public IDExpression copy(DCopyOptions var1) {
      if (var1 != null) {
         IDExpression var2 = var1.onDup(this);
         if (var2 != null) {
            return var2;
         }
      }

      bpp var3 = new bpp(this.wS, pC(this.UT, var1), this.pC, this.kS, this.A);
      super.A(var3);
      return var3;
   }

   @Override
   public IDCallInfo duplicate() {
      return (IDCallInfo)this.copy(null);
   }

   protected void pC(bpp var1, DCopyOptions var2) {
      var1.wS = this.wS;
      var1.UT = pC(this.UT, var2);
      var1.kS = this.kS;
      var1.A = this.A;
      super.A(var1);
   }

   @Override
   public String getMethodSignature() {
      return this.kS;
   }

   @Override
   public String getMethodName() {
      return this.sY;
   }

   @Override
   public int getCountOfArguments() {
      return this.UT.length;
   }

   // $VF: Unable to simplify switch on enum
   // Please report this to the Vineflower issue tracker, at https://github.com/Vineflower/vineflower/issues with a copy of the class file (if you have the rights to distribute it!)
   @Override
   public boolean hasThis() {
      switch (this.A) {
         case DIRECT:
         case SUPER:
         case VIRTUAL:
         case INTERFACE:
            return true;
         default:
            return false;
      }
   }

   @Override
   public List getArguments() {
      return Arrays.asList(this.UT);
   }

   @Override
   public void setArgument(int var1, IDExpression var2) {
      this.UT[var1] = var2;
   }

   @Override
   public IDExpression getArgument(int var1) {
      return this.UT[var1];
   }

   @Override
   public DInvokeType getInvokeType() {
      return this.A;
   }

   @Override
   public void collectVarIds(Set var1) {
      for (IDExpression var5 : this.UT) {
         var5.collectVarIds(var1);
      }
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      for (IDExpression var5 : this.UT) {
         var5.updateTypes(var1);
      }

      if (this.A != DInvokeType.LAMBDA && this.A != DInvokeType.CUSTOM && this.A != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var11 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.A != DInvokeType.STATIC && this.A != DInvokeType.NEW) {
            var11.partypes.add(0, var11.csig);
         }

         IJavaTypeFactory var12 = var1.getTypeFactory();
         int var13 = 0;

         for (IDExpression var8 : this.UT) {
            IJavaType var9 = var12.createType((String)var11.partypes.get(var13));
            IJavaType var10 = var8.getType();
            if (!var10.equals(var9) && !var10.isDefined()) {
               var10 = var9;
               if (var9.isSmallInt()) {
                  var10 = var12.getSmallIntWildcard();
               } else if (var9.isClassOrInterface()) {
                  var10 = var12.createWildcardType(var9.getName(), true);
               }

               var8.setType(var10, var1);
            }

            var13++;
         }
      }
   }

   @Override
   public void verify() {
      if (this.A != DInvokeType.LAMBDA && this.A != DInvokeType.CUSTOM && this.A != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var1 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.A != DInvokeType.STATIC && this.A != DInvokeType.NEW) {
            var1.partypes.add(0, var1.csig);
         }

         if (this.UT.length != var1.partypes.size()) {
            throw new IllegalStateException();
         }
      }
   }

   @Override
   public void upgradeMistypedArguments(DTypeInfo var1, IDGlobalContext var2) {
      if (this.A != DInvokeType.LAMBDA && this.A != DInvokeType.CUSTOM && this.A != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var3 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.A != DInvokeType.STATIC && this.A != DInvokeType.NEW) {
            var3.partypes.add(0, var3.csig);
         }

         if (this.UT.length != var3.partypes.size()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("unexpected parameter count"), this.getMethodSignature());
         } else {
            IJavaTypeFactory var4 = var2.getTypeFactory();
            int var5 = 0;

            for (IDExpression var9 : this.UT) {
               IJavaType var10 = var4.createType((String)var3.partypes.get(var5));
               IJavaType var11 = var9.getType();
               if (!var10.equals(var11)) {
                  Object var12 = null;
                  if (var9 instanceof IDImm) {
                     var12 = ((IDImm)var9).duplicateWithDifferentType(var10);
                  } else if (!var2.getTypeInfoProvider().isCompatible(var11.getName(), var10.getName())) {
                     var12 = var2.createCastOperation(var10, var9);
                  }

                  if (var12 != null) {
                     this.UT[var5] = (IDExpression)var12;
                  }
               }

               var5++;
            }
         }
      }
   }

   @Override
   public boolean pC() {
      return true;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      for (IDExpression var5 : this.UT) {
         if (var5.canThrow(var1)) {
            return true;
         }
      }

      if (this.kS.startsWith("Ljeb/synthetic/") && this.sY.endsWith("$NT")) {
         return false;
      } else {
         Boolean var7 = this.getCustomCanThrow();
         if (var7 != null) {
            return var7;
         } else {
            if (this.hasThis()) {
               if (this.UT.length == 0) {
                  return true;
               }

               IDExpression var8 = this.UT[0];
               if (!(var8 instanceof IDImm var10 && var10.isNonNullRef())
                  && !(var8 instanceof IDStaticField var13 && var13.isTypeClass())
                  && !Boolean.TRUE.equals(this.getData("THIS_NOTNULL"))) {
                  return true;
               }
            }

            String var9 = this.kS;
            if (this.A != DInvokeType.STATIC && this.A != DInvokeType.DIRECT) {
               com.pnfsoftware.jeb.corei.parsers.dex.vi var11 = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1.getDex();
               bfu var14 = var11.wS(this.kS);
               if (var14 != null && var14.isInternal()) {
                  if (this.getPhysicalOffset() < 0) {
                     return true;
                  }

                  int var6 = var11.Er().pC(var1.getMethod(), this.getPhysicalOffset());
                  if (var6 < 0) {
                     return true;
                  }

                  var9 = var11.sY(var6).getSignature(false);
               }
            }

            IDexContextInfoProvider var12 = var1.getDex().getContextInfoProvider();
            ContextAccessType var15 = var12.getMethodCAT(var9);
            return !var15.isNothrow();
         }
      }
   }

   @Override
   public boolean hasSideEffects(IDMethodContext var1, boolean var2) {
      for (IDExpression var6 : this.UT) {
         if (var6.hasSideEffects(var1, var2)) {
            return true;
         }
      }

      Boolean var8 = this.A();
      if (var8 != null) {
         return var8;
      } else {
         if (var2 && this.hasThis()) {
            if (this.UT.length == 0) {
               return true;
            }

            IDExpression var9 = this.UT[0];
            if (!(var9 instanceof IDImm var11 && var11.isNonNullRef())
               && !(var9 instanceof IDStaticField var14 && var14.isTypeClass())
               && !Boolean.TRUE.equals(this.getData("THIS_NOTNULL"))) {
               return true;
            }
         }

         String var10 = this.kS;
         if (this.A != DInvokeType.STATIC && this.A != DInvokeType.DIRECT) {
            com.pnfsoftware.jeb.corei.parsers.dex.vi var12 = (com.pnfsoftware.jeb.corei.parsers.dex.vi)var1.getDex();
            bfu var15 = var12.wS(this.kS);
            if (var15 != null && var15.isInternal()) {
               if (this.getPhysicalOffset() < 0) {
                  return true;
               }

               int var7 = var12.Er().pC(var1.getMethod(), this.getPhysicalOffset());
               if (var7 < 0) {
                  return true;
               }

               var10 = var12.sY(var7).getSignature(false);
            }
         }

         IDexContextInfoProvider var13 = var1.getDex().getContextInfoProvider();
         ContextAccessType var16 = var13.getMethodCAT(var10);
         return var2 && !var16.isNothrow() ? true : !var16.isSEF();
      }
   }

   @Override
   public int countVariable(IDVar var1) {
      int var2 = 0;

      for (IDExpression var6 : this.UT) {
         var2 += ((bph)var6).countVariable(var1);
      }

      return var2;
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < this.UT.length; var4++) {
         IDExpression var5 = this.UT[var4];
         if (var5 == var1) {
            this.UT[var4] = var2.duplicate();
            var3++;
         } else {
            var3 += var5.replaceVariable(var1, var2);
         }
      }

      return var3;
   }

   @Override
   public void pC(IJavaType var1, IJavaType var2) {
      super.pC(var1, var2);

      for (IDExpression var6 : this.UT) {
         ((bph)var6).pC(var1, var2);
      }
   }

   @Override
   public void collectSubExpressions(Collection var1) {
      var1.addAll(this.getArguments());
   }

   @Override
   public boolean replaceSubExpression(IDExpression var1, IDExpression var2) {
      if (var2 == null) {
         return false;
      } else {
         int var3 = 0;

         for (int var4 = 0; var4 < this.UT.length; var4++) {
            if (this.UT[var4] == var1) {
               this.UT[var4] = pC(var2, var3);
               var3++;
            }
         }

         return var3 > 0;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      btp var2 = (btp)var1;
      IDImm var3 = var2.pC(this.kS, Arrays.asList(this.UT), this.A, null);
      IDEmuFrame var4 = var2.getCurrentFrame();
      if (var4 != null && var4.getMethodSignature().contains("<clinit>")) {
         JvmMethodSig var5 = JvmMethodSig.parse(var4.getMethodSignature());
         btj var6 = var2.UT(var5.csig);
         if (var6 != null) {
            var6.pC(this, var3);
         }
      }

      return var3;
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      IDexMethod var3 = var1.getDex().getMethod(this.wS.getValue());
      String var4 = var3.getSignature(false);
      if (!var3.isInternal()) {
         ((com.pnfsoftware.jeb.corei.parsers.dexdec.Ws)var1.getGlobalContext().getDecompiler()).pC(var4, Boolean.valueOf(this.A == DInvokeType.STATIC));
      }

      byte var5 = 0;
      if (this.A == DInvokeType.SUPER) {
         var5 = 1;
      } else if (this.A == DInvokeType.LAMBDA) {
         var5 = 2;
         ((bjr)var1.getGlobalContext().getDecompiler().getMethod(var4, true)).addFlags(16);
         bpl.A(var1.getGlobalContext(), var3);
      } else if (this.A == DInvokeType.SUPER) {
         var5 = 1;
      }

      if (this.A == DInvokeType.STATIC) {
         var5 = 3;
      }

      bit var6 = (bit)var2.getGlobalContext().createCall(var4, var5);
      return this.pC(var1, var2, var6);
   }

   protected IJavaExpression pC(IDMethodContext var1, IJavaMethod var2, bit var3) {
      if (this.A != DInvokeType.LAMBDA && this.A != DInvokeType.CUSTOM && this.A != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var15 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.A != DInvokeType.STATIC && this.A != DInvokeType.NEW) {
            var15.partypes.add(0, var15.csig);
         }

         if (this.UT.length != var15.partypes.size()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(new RuntimeException("unexpected parameter count"), this.getMethodSignature());
         }

         cdk var16 = (cdk)var1.getTypeFactory();
         byte var17 = 0;

         for (Object var10 : this.UT) {
            IJavaType var11 = var16.createType((String)var15.partypes.get(var17));
            ((IDExpression)var10).getType();
            if (!(var10 instanceof IDImm) || var11 != var16.kS && var11 != var16.UT) {
               if (var10 instanceof bqa && ((bqa)var10).isCast()) {
                  IJavaType var13 = ((bqa)var10).getOperator().getCastType();
                  if (var13 == var11) {
                     IJavaType var12 = ((bqa)var10).getRight().getType();
                     boolean var14 = false;
                     if (var11 == var16.UT) {
                        if (var12 == var16.kS) {
                           var14 = true;
                        }
                     } else if (var11 == var16.E) {
                        if (var12 == var16.kS || var12 == var16.wS || var12 == var16.UT) {
                           var14 = true;
                        }
                     } else if (var11 == var16.sY) {
                        if (var12 == var16.kS || var12 == var16.wS || var12 == var16.UT || var12 == var16.E) {
                           var14 = true;
                        }
                     } else if (var11 == var16.ys) {
                        if (var12 == var16.kS || var12 == var16.wS || var12 == var16.UT || var12 == var16.E || var12 == var16.sY) {
                           var14 = true;
                        }
                     } else if (var11 == var16.ld
                        && (var12 == var16.kS || var12 == var16.wS || var12 == var16.UT || var12 == var16.E || var12 == var16.sY || var12 == var16.ys)) {
                        var14 = true;
                     }

                     if (var14) {
                        var10 = ((bqa)var10).getRight();
                     }
                  }
               }
            } else {
               var10 = var1.getGlobalContext().createCastOperation(var11, (IDExpression)var10);
            }

            IJavaExpression var20 = (IJavaExpression)((IDExpression)var10).generateAST(var1, var2);
            var3.addArgument(var20);
         }
      } else {
         for (IDExpression var7 : this.UT) {
            IJavaExpression var8 = (IJavaExpression)var7.generateAST(var1, var2);
            var3.addArgument(var8);
         }
      }

      var3.pC(var1.getGlobalContext().getDecompiler());
      var3.setOrigin(this.getOrigin());
      return (IJavaExpression)this.pC(var3);
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append(this.sY);
      var1.paren();
      int var2 = 0;

      for (IDExpression var6 : this.UT) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var6.format(var1);
         var2++;
      }

      var1.parenClose();
      var1.appendFormattedTypeIf(this.pC);
   }
}
