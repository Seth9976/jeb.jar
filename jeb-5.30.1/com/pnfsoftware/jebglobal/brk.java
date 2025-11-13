package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IVisitResults;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexClass;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.java.IJVisitor;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaClass;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaNew;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.core.units.code.java.JavaReconLambda;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class brk implements IJVisitor {
   brk(brj var1) {
      this.q = var1;
   }

   public void q(IJavaElement var1, IJavaElement var2, IVisitResults var3) {
      List var5 = null;
      IJavaMethod var6 = null;
      IJavaType var4;
      if (var1 instanceof IJavaStaticField var8 && !var8.isPseudoFieldTypeClass()) {
         var4 = var8.getClassType();
         if (var8.getField().getType() != var4) {
            return;
         }
      } else {
         if (!(var1 instanceof IJavaNew var7)) {
            return;
         }

         var4 = var7.getType();
         var5 = var7.getArguments();
         var6 = var7.getConstructor();
      }

      if (this.q.jctx.getClassFactory().get(var4.getSignature()) != this.q.c) {
         if (var1.getReconLambda() == null) {
            String var23 = this.q.q(var4);
            if (var23 != null) {
               String var9 = var4.getSignature() + "->" + var23;
               IDexMethod var10 = this.q.dex.getMethod(var9);
               if (var10 != null && var10.isInternal()) {
                  IDexMethodData var11 = var10.getData();
                  if (!var11.isAbstract()) {
                     if (var11.getCodeItem() != null && var11.getCodeItem().getInstructions().size() < 40) {
                        int var12 = 0;
                        IDexClass var13 = this.q.dex.getClass(var4.getSignature());
                        if (var13 != null) {
                           for (IDexMethod var15 : var13.getMethods()) {
                              if (var15.getData() != null && var15.getData().getCodeItem() != null) {
                                 var12 += var15.getData().getCodeItem().getInstructionsSize();
                              }
                           }
                        }

                        if (var12 < 200) {
                           IJavaClass var24 = this.q.jctx.getClassFactory().get(var4.getSignature());
                           if (var24 != null && var24.isBuilt()) {
                              IJavaMethod var25 = this.q.q(var24, var23);
                              if (var25 != null && !var25.isAbstract() && var25.getBody().size() == 1) {
                                 IJavaCall var16 = this.q.q(var25.getBody().get(0));
                                 IJavaMethod var18;
                                 if (var16 != null && !(var18 = var16.getMethod()).isExternal() && var18.getClassType() == this.q.c.getType()) {
                                    Object[] var10000 = new Object[]{var4};
                                    HashMap var26 = new HashMap();
                                    if (var6 == null || this.q.q(var6, var5, var26)) {
                                       ArrayList var27 = new ArrayList();
                                       HashMap var28 = new HashMap();
                                       if (this.q.q(var25, var16, var26, var27, var28)) {
                                          var18.addFlags(16);
                                          com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(this.q.decomp, this.q.dex.getMethod(var18.getSignature()));
                                          var24.addFlags(32);
                                          JavaReconLambda var29 = new JavaReconLambda(var4, var18.getSignature(), var27, var28);
                                          var1.setLambdaRecon(var29);
                                          this.q.q++;
                                       }
                                    }
                                 } else {
                                    if (var6 != null) {
                                       return;
                                    }

                                    HashMap var19 = new HashMap();
                                    ArrayList var20 = new ArrayList();
                                    HashMap var21 = new HashMap();
                                    if (this.q.q(var25, var16, var19, var20, var21)) {
                                       var25.addFlags(16);
                                       com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(this.q.decomp, this.q.dex.getMethod(var25.getSignature()));
                                       var24.addFlags(32);
                                       JavaReconLambda var22 = new JavaReconLambda(var4, var25.getSignature(), var20, var21);
                                       var1.setLambdaRecon(var22);
                                       this.q.q++;
                                    }
                                 }
                              }
                           } else {
                              if (this.q.drcollector != null) {
                                 this.q.drcollector.request(var4.getSignature());
                              }
                           }
                        }
                     }
                  }
               }
            }
         }
      }
   }
}
