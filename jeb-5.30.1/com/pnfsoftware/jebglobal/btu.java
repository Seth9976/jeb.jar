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

public class btu extends btk implements IDCallInfo {
   private static final ILogger gO = GlobalLog.getLogger(btu.class);
   static final boolean RF = false;
   DInvokeType xK;
   String Dw;
   IDIndex Uv;
   IDExpression[] oW;
   private String nf;

   btu(IDIndex var1, IDExpression[] var2, IJavaType var3, String var4, DInvokeType var5) {
      super(var3);
      if (var5 != null && var1 != null && var4 != null) {
         if (var2 == null) {
            var2 = new IDExpression[0];
         }

         this.Uv = var1;
         this.oW = var2;
         this.Dw = var4;
         this.xK = var5;
         int var6 = var4.indexOf("->");
         if (var6 < 0) {
            throw new IllegalArgumentException();
         } else {
            var6 += 2;
            int var7 = var4.indexOf("(", var6);
            if (var7 < 0) {
               throw new IllegalArgumentException();
            } else {
               this.nf = var4.substring(var6, var7);
            }
         }
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public int hashCode() {
      int var1 = super.hashCode();
      var1 = 31 * var1 + Arrays.hashCode((Object[])this.oW);
      var1 = 31 * var1 + (this.xK == null ? 0 : this.xK.hashCode());
      var1 = 31 * var1 + (this.Uv == null ? 0 : this.Uv.hashCode());
      return 31 * var1 + (this.Dw == null ? 0 : this.Dw.hashCode());
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
         btu var3 = (btu)var1;
         if (!q(this.oW, var3.oW, var2)) {
            return false;
         } else if (this.xK != var3.xK) {
            return false;
         } else {
            if (this.Uv == null) {
               if (var3.Uv != null) {
                  return false;
               }
            } else if (!this.Uv.equalsEx(var3.Uv, var2)) {
               return false;
            }

            if (this.Dw == null) {
               if (var3.Dw != null) {
                  return false;
               }
            } else if (!this.Dw.equals(var3.Dw)) {
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

      btu var3 = new btu(this.Uv, q(this.oW, var1), this.q, this.Dw, this.xK);
      super.RF(var3);
      return var3;
   }

   @Override
   public IDCallInfo duplicate() {
      return (IDCallInfo)this.copy(null);
   }

   protected void q(btu var1, DCopyOptions var2) {
      var1.Uv = this.Uv;
      var1.oW = q(this.oW, var2);
      var1.Dw = this.Dw;
      var1.xK = this.xK;
      super.RF(var1);
   }

   @Override
   public String getMethodSignature() {
      return this.Dw;
   }

   @Override
   public String getMethodName() {
      return this.nf;
   }

   @Override
   public int getCountOfArguments() {
      return this.oW.length;
   }

   @Override
   public boolean hasThis() {
      switch (this.xK) {
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
      return Arrays.asList(this.oW);
   }

   @Override
   public void setArgument(int var1, IDExpression var2) {
      this.oW[var1] = var2;
   }

   @Override
   public IDExpression getArgument(int var1) {
      return this.oW[var1];
   }

   @Override
   public DInvokeType getInvokeType() {
      return this.xK;
   }

   @Override
   public void collectVarIds(Set var1) {
      for (IDExpression var5 : this.oW) {
         var5.collectVarIds(var1);
      }
   }

   @Override
   public void updateTypes(DTypeInfo var1) {
      for (IDExpression var5 : this.oW) {
         var5.updateTypes(var1);
      }

      if (this.xK != DInvokeType.LAMBDA && this.xK != DInvokeType.CUSTOM && this.xK != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var11 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.xK != DInvokeType.STATIC && this.xK != DInvokeType.NEW) {
            var11.partypes.add(0, var11.csig);
         }

         IJavaTypeFactory var12 = var1.getTypeFactory();
         int var13 = 0;

         for (IDExpression var8 : this.oW) {
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
   public boolean q() {
      if (this.q.isLegal() && !this.q.isDetermined()) {
         return false;
      } else {
         for (IDExpression var4 : this.oW) {
            if (!((btk)var4).q()) {
               return false;
            }
         }

         return true;
      }
   }

   @Override
   public void verify() {
      if (this.xK != DInvokeType.LAMBDA && this.xK != DInvokeType.CUSTOM && this.xK != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var1 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.xK != DInvokeType.STATIC && this.xK != DInvokeType.NEW) {
            var1.partypes.add(0, var1.csig);
         }

         if (this.oW.length != var1.partypes.size()) {
            throw new IllegalStateException();
         }
      }
   }

   @Override
   public void upgradeMistypedArguments(DTypeInfo var1, IDGlobalContext var2) {
      if (this.xK != DInvokeType.LAMBDA && this.xK != DInvokeType.CUSTOM && this.xK != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var3 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.xK != DInvokeType.STATIC && this.xK != DInvokeType.NEW) {
            var3.partypes.add(0, var3.csig);
         }

         if (this.oW.length != var3.partypes.size()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("unexpected parameter count"), this.getMethodSignature());
         } else {
            IJavaTypeFactory var4 = var2.getTypeFactory();
            int var5 = 0;

            for (IDExpression var9 : this.oW) {
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
                     this.oW[var5] = (IDExpression)var12;
                  }
               }

               var5++;
            }
         }
      }
   }

   @Override
   public boolean RF() {
      return true;
   }

   @Override
   public boolean xK() {
      for (IDExpression var4 : this.oW) {
         if (((btk)var4).xK()) {
            return true;
         }
      }

      return false;
   }

   @Override
   public boolean canThrow(IDMethodContext var1) {
      for (IDExpression var5 : this.oW) {
         if (var5.canThrow(var1)) {
            return true;
         }
      }

      if (this.Dw.startsWith("Ljeb/synthetic/") && this.nf.endsWith("$NT")) {
         return false;
      } else {
         Boolean var7 = this.getCustomCanThrow();
         if (var7 != null) {
            return var7;
         } else {
            if (this.hasThis()) {
               if (this.oW.length == 0) {
                  return true;
               }

               IDExpression var8 = this.oW[0];
               if (!(var8 instanceof IDImm var10 && var10.isNonNullRef())
                  && !(var8 instanceof IDStaticField var13 && var13.isTypeClass())
                  && !Boolean.TRUE.equals(this.getData("THIS_NOTNULL"))) {
                  return true;
               }
            }

            String var9 = this.Dw;
            if (this.xK != DInvokeType.STATIC && this.xK != DInvokeType.DIRECT) {
               com.pnfsoftware.jeb.corei.parsers.dex.bK var11 = (com.pnfsoftware.jeb.corei.parsers.dex.bK)var1.getDex();
               bjp var14 = var11.Dw(this.Dw);
               if (var14 != null && var14.isInternal()) {
                  if (this.getPhysicalOffset() < 0) {
                     return true;
                  }

                  int var6 = var11.Rr().q(var1.getMethod(), this.getPhysicalOffset());
                  if (var6 < 0) {
                     return true;
                  }

                  var9 = var11.gO(var6).getSignature(false);
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
      for (IDExpression var6 : this.oW) {
         if (var6.hasSideEffects(var1, var2)) {
            return true;
         }
      }

      Boolean var8 = this.Dw();
      if (var8 != null) {
         return var8;
      } else {
         if (var2 && this.hasThis()) {
            if (this.oW.length == 0) {
               return true;
            }

            IDExpression var9 = this.oW[0];
            if (!(var9 instanceof IDImm var11 && var11.isNonNullRef())
               && !(var9 instanceof IDStaticField var14 && var14.isTypeClass())
               && !Boolean.TRUE.equals(this.getData("THIS_NOTNULL"))) {
               return true;
            }
         }

         String var10 = this.Dw;
         if (this.xK != DInvokeType.STATIC && this.xK != DInvokeType.DIRECT) {
            com.pnfsoftware.jeb.corei.parsers.dex.bK var12 = (com.pnfsoftware.jeb.corei.parsers.dex.bK)var1.getDex();
            bjp var15 = var12.Dw(this.Dw);
            if (var15 != null && var15.isInternal()) {
               if (this.getPhysicalOffset() < 0) {
                  return true;
               }

               int var7 = var12.Rr().q(var1.getMethod(), this.getPhysicalOffset());
               if (var7 < 0) {
                  return true;
               }

               var10 = var12.gO(var7).getSignature(false);
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

      for (IDExpression var6 : this.oW) {
         var2 += ((btk)var6).countVariable(var1);
      }

      return var2;
   }

   @Override
   public int replaceVariable(IDVar var1, IDExpression var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < this.oW.length; var4++) {
         IDExpression var5 = this.oW[var4];
         if (var5 == var1) {
            this.oW[var4] = var2.duplicate();
            var3++;
         } else {
            var3 += var5.replaceVariable(var1, var2);
         }
      }

      return var3;
   }

   @Override
   public void q(IJavaType var1, IJavaType var2) {
      super.q(var1, var2);

      for (IDExpression var6 : this.oW) {
         ((btk)var6).q(var1, var2);
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

         for (int var4 = 0; var4 < this.oW.length; var4++) {
            if (this.oW[var4] == var1) {
               this.oW[var4] = q(var2, var3);
               var3++;
            }
         }

         return var3 > 0;
      }
   }

   @Override
   public IDImm evaluate(IDState var1) throws DexDecEvaluationException {
      bye var2 = (bye)var1;
      IDImm var3 = var2.q(this.Dw, Arrays.asList(this.oW), this.xK, null);
      IDEmuFrame var4 = var2.getCurrentFrame();
      if (var4 != null && var4.getMethodSignature().contains("<clinit>")) {
         JvmMethodSig var5 = JvmMethodSig.parse(var4.getMethodSignature());
         bxy var6 = var2.Uv(var5.csig);
         if (var6 != null) {
            var6.q(this, var3);
         }
      }

      return var3;
   }

   @Override
   public IJavaElement generateAST(IDMethodContext var1, IJavaMethod var2) {
      IDexMethod var3 = var1.getDex().getMethod(this.Uv.getValue());
      String var4 = var3.getSignature(false);
      if (!var3.isInternal()) {
         ((com.pnfsoftware.jeb.corei.parsers.dexdec.ej)var1.getGlobalContext().getDecompiler()).q(var4, Boolean.valueOf(this.xK == DInvokeType.STATIC));
      }

      byte var5 = 0;
      if (this.xK == DInvokeType.SUPER) {
         var5 = 1;
      } else if (this.xK == DInvokeType.LAMBDA) {
         var5 = 2;
         ((bno)var1.getGlobalContext().getDecompiler().getMethod(var4, true)).addFlags(16);
         bto.RF(var1.getGlobalContext(), var3);
      } else if (this.xK == DInvokeType.SUPER) {
         var5 = 1;
      }

      if (this.xK == DInvokeType.STATIC) {
         var5 = 3;
      }

      bmq var6 = (bmq)var2.getGlobalContext().createCall(var4, var5);
      return this.q(var1, var2, var6);
   }

   protected IJavaExpression q(IDMethodContext var1, IJavaMethod var2, bmq var3) {
      if (this.xK != DInvokeType.LAMBDA && this.xK != DInvokeType.CUSTOM && this.xK != DInvokeType.POLYMORPHIC) {
         JvmMethodSig var15 = JvmMethodSig.parse(this.getMethodSignature());
         if (this.xK != DInvokeType.STATIC && this.xK != DInvokeType.NEW) {
            var15.partypes.add(0, var15.csig);
         }

         if (this.oW.length != var15.partypes.size()) {
            com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(new RuntimeException("unexpected parameter count"), this.getMethodSignature());
         }

         cis var16 = (cis)var1.getTypeFactory();
         byte var17 = 0;

         for (Object var10 : this.oW) {
            IJavaType var11 = var16.createType((String)var15.partypes.get(var17));
            ((IDExpression)var10).getType();
            if (!(var10 instanceof IDImm) || var11 != var16.xK && var11 != var16.Uv) {
               if (var10 instanceof bug && ((bug)var10).isCast()) {
                  IJavaType var13 = ((bug)var10).getOperator().getCastType();
                  if (var13 == var11) {
                     IJavaType var12 = ((bug)var10).getRight().getType();
                     boolean var14 = false;
                     if (var11 == var16.Uv) {
                        if (var12 == var16.xK) {
                           var14 = true;
                        }
                     } else if (var11 == var16.oW) {
                        if (var12 == var16.xK || var12 == var16.Dw || var12 == var16.Uv) {
                           var14 = true;
                        }
                     } else if (var11 == var16.gO) {
                        if (var12 == var16.xK || var12 == var16.Dw || var12 == var16.Uv || var12 == var16.oW) {
                           var14 = true;
                        }
                     } else if (var11 == var16.nf) {
                        if (var12 == var16.xK || var12 == var16.Dw || var12 == var16.Uv || var12 == var16.oW || var12 == var16.gO) {
                           var14 = true;
                        }
                     } else if (var11 == var16.gP
                        && (var12 == var16.xK || var12 == var16.Dw || var12 == var16.Uv || var12 == var16.oW || var12 == var16.gO || var12 == var16.nf)) {
                        var14 = true;
                     }

                     if (var14) {
                        var10 = ((bug)var10).getRight();
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
         for (IDExpression var7 : this.oW) {
            IJavaExpression var8 = (IJavaExpression)var7.generateAST(var1, var2);
            var3.addArgument(var8);
         }
      }

      var3.q(var1.getGlobalContext().getDecompiler());
      var3.setOrigin(this.getOrigin());
      return (IJavaExpression)this.q(var3);
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append(this.nf);
      var1.paren();
      int var2 = 0;

      for (IDExpression var6 : this.oW) {
         if (var2 > 0) {
            var1.append(", ");
         }

         var6.format(var1);
         var2++;
      }

      var1.parenClose();
      var1.appendFormattedTypeIf(this.q);
   }
}
