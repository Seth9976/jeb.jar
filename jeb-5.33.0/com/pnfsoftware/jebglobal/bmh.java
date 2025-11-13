package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethod;
import com.pnfsoftware.jeb.core.units.code.android.dex.IDexMethodData;
import com.pnfsoftware.jeb.core.units.code.java.AbstractJBlockOptimizer;
import com.pnfsoftware.jeb.core.units.code.java.IJavaAssignment;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaCall;
import com.pnfsoftware.jeb.core.units.code.java.IJavaDefinition;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaIdentifier;
import com.pnfsoftware.jeb.core.units.code.java.IJavaInstanceField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaMethod;
import com.pnfsoftware.jeb.core.units.code.java.IJavaReturn;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStaticField;
import com.pnfsoftware.jeb.core.units.code.java.JUtil;
import com.pnfsoftware.jeb.util.base.Wrapper;
import java.util.List;

public class bmh extends AbstractJBlockOptimizer {
   @Override
   public int optimizeBlock(IJavaBlock var1, IJavaElement var2) {
      int var3 = 0;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         IJavaStatement var5 = var1.get(var4);
         var3 += this.pC(var1, var5);
      }

      return var3;
   }

   private int pC(IJavaElement var1, IJavaElement var2) {
      if (JUtil.isClassMethodField(var2)) {
         return 0;
      } else {
         Wrapper var3 = null;
         if (var2 instanceof IJavaCall var4) {
            var3 = this.pC(var4, var1);
         }

         int var7 = 0;
         if (var3 != null) {
            var2 = (IJavaElement)var3.get();
            var7++;
            if (var2 == null) {
               return var7;
            }
         }

         for (IJavaElement var6 : var2.getSubElements()) {
            var7 += this.pC(var2, var6);
         }

         return var7;
      }
   }

   private Wrapper pC(IJavaCall var1, IJavaElement var2) {
      if (!var1.isStaticCall()) {
         return null;
      } else {
         IDexMethod var3 = this.dex.getMethod(var1.getMethodSignature());
         if (var3 != null && var3.isInternal()) {
            IDexMethodData var4 = var3.getData();
            if ((var4.getAccessFlags() & 4096) == 0) {
               return null;
            } else if (var4.getCodeItem() != null && var4.getCodeItem().getInstructions().size() < 40) {
               IJavaMethod var5 = var1.getMethod();
               if (var5 != null && var5.isBuilt()) {
                  Object var9 = null;
                  String var6;
                  if ((var6 = this.pC(var5)) != null) {
                     var9 = this.jctx
                        .createAssignment(
                           this.jctx.createInstanceField(((IJavaExpression)var1.getArguments().get(0)).duplicate(), var6),
                           ((IJavaExpression)var1.getArguments().get(1)).duplicate()
                        );
                  } else {
                     IJavaStaticField var7;
                     if ((var7 = this.A(var5)) != null) {
                        var9 = this.jctx.createAssignment(var7.duplicate(), ((IJavaExpression)var1.getArguments().get(0)).duplicate());
                     } else if ((var6 = this.kS(var5)) != null) {
                        var9 = this.jctx.createInstanceField(((IJavaExpression)var1.getArguments().get(0)).duplicate(), var6);
                     } else if ((var7 = this.wS(var5)) != null) {
                        var9 = var7.duplicate();
                     } else {
                        IJavaCall var8;
                        if ((var8 = this.UT(var5)) != null) {
                           var9 = this.jctx.createCall(var8.getMethodSignature(), var8.getCallType(), bhu.pC(var1.getArguments()));
                        }
                     }
                  }

                  if (var9 == null) {
                     return null;
                  } else {
                     try {
                        if (var9.equals(var1)) {
                           return null;
                        }
                     } catch (Exception var10) {
                        return null;
                     }

                     try {
                        if (!var2.replaceSubElement(var1, (IJavaElement)var9)) {
                           return null;
                        }
                     } catch (ClassCastException var11) {
                        if (var2 instanceof IJavaBlock) {
                           ((IJavaBlock)var2).remove(var1);
                           return Wrapper.wrap(null);
                        }

                        return null;
                     }

                     var5.markOptionalRendering();
                     com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(this.decomp, this.dex.getMethod(var5.getSignature()), S.L("Synthetic accessor"));
                     return Wrapper.wrap(var9);
                  }
               } else {
                  if (this.drcollector != null) {
                     this.drcollector.request(var1.getMethodSignature());
                  }

                  return null;
               }
            } else {
               return null;
            }
         } else {
            return null;
         }
      }
   }

   private String pC(IJavaMethod var1) {
      IJavaBlock var2 = var1.getBody();
      if ((var2.size() == 1 || var2.size() == 2 && var2.get(1) instanceof IJavaReturn) && var2.get(0) instanceof IJavaAssignment) {
         IJavaAssignment var3 = (IJavaAssignment)var2.get(0);
         if (var3.isSimpleAssignment()) {
            List var4 = var1.getParameters();
            if (var4.size() == 2 && var3.getLeft() instanceof IJavaInstanceField && var3.getRight() instanceof IJavaIdentifier) {
               IJavaInstanceField var5 = (IJavaInstanceField)var3.getLeft();
               IJavaIdentifier var6 = (IJavaIdentifier)var3.getRight();
               if (var5.getInstance() == ((IJavaDefinition)var4.get(0)).getIdentifier() && var6 == ((IJavaDefinition)var4.get(1)).getIdentifier()) {
                  return var5.getFieldSignature();
               }
            }
         }
      }

      return null;
   }

   private IJavaStaticField A(IJavaMethod var1) {
      IJavaBlock var2 = var1.getBody();
      if ((var2.size() == 1 || var2.size() == 2 && var2.get(1) instanceof IJavaReturn) && var2.get(0) instanceof IJavaAssignment) {
         IJavaAssignment var3 = (IJavaAssignment)var2.get(0);
         if (var3.isSimpleAssignment()) {
            List var4 = var1.getParameters();
            if (var4.size() == 1 && var3.getLeft() instanceof IJavaStaticField && var3.getRight() instanceof IJavaIdentifier) {
               IJavaStaticField var5 = (IJavaStaticField)var3.getLeft();
               IJavaIdentifier var6 = (IJavaIdentifier)var3.getRight();
               if (var6 == ((IJavaDefinition)var4.get(0)).getIdentifier()) {
                  return var5;
               }
            }
         }
      }

      return null;
   }

   private String kS(IJavaMethod var1) {
      IJavaBlock var2 = var1.getBody();
      if (var2.size() == 1 && var2.get(0) instanceof IJavaReturn) {
         IJavaReturn var3 = (IJavaReturn)var2.get(0);
         if (var3.getExpression() instanceof IJavaInstanceField) {
            IJavaInstanceField var4 = (IJavaInstanceField)var3.getExpression();
            List var5 = var1.getParameters();
            if (var5.size() == 1 && var4.getInstance() == ((IJavaDefinition)var5.get(0)).getIdentifier()) {
               return var4.getFieldSignature();
            }
         }
      }

      return null;
   }

   private IJavaStaticField wS(IJavaMethod var1) {
      IJavaBlock var2 = var1.getBody();
      if (var2.size() == 1 && var2.get(0) instanceof IJavaReturn) {
         IJavaReturn var3 = (IJavaReturn)var2.get(0);
         if (var3.getExpression() instanceof IJavaStaticField) {
            List var4 = var1.getParameters();
            if (var4.size() == 0) {
               return (IJavaStaticField)var3.getExpression();
            }
         }
      }

      return null;
   }

   private IJavaCall UT(IJavaMethod var1) {
      IJavaBlock var2 = var1.getBody();
      if (var2.size() == 1) {
         IJavaCall var3 = null;
         if (var2.get(0) instanceof IJavaCall) {
            var3 = (IJavaCall)var2.get(0);
         } else if (var2.get(0) instanceof IJavaReturn) {
            IJavaReturn var4 = (IJavaReturn)var2.get(0);
            if (var4.getExpression() instanceof IJavaCall) {
               var3 = (IJavaCall)var4.getExpression();
            }
         }

         if (var3 != null) {
            List var7 = var1.getParameters();
            List var5 = var3.getArguments();
            if (var7.size() == var5.size()) {
               for (int var6 = 0; var6 < var7.size(); var6++) {
                  if (((IJavaDefinition)var7.get(var6)).getIdentifier() != var5.get(var6)) {
                     return null;
                  }
               }

               return var3;
            }
         }
      }

      return null;
   }
}
