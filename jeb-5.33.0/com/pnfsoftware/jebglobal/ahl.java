package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConditionalStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICControlBreaker;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCustomStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericBreakable;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGenericLoop;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICSwitchStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.opt.AbstractCBlockOptimizer;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ahl extends AbstractCBlockOptimizer {
   private static final StructuredLogger pC = aco.pC(ahl.class);
   private List A;
   private List kS;
   private List wS;

   @Override
   public int perform() {
      boolean var1 = true;
      int var2 = 0;

      while (var1) {
         this.A = new ArrayList();
         this.kS = new ArrayList();
         this.wS = new ArrayList();
         super.performOnMethoBlocksRecursivively();
         if (this.pC(this.A, true)) {
            var2++;
         } else if (this.pC(this.kS, false)) {
            var2++;
         } else {
            var1 = false;
         }
      }

      return var2;
   }

   @Override
   protected int optimizeBlock(ICBlock var1) {
      for (int var2 = 0; var2 < var1.size(); var2++) {
         if (var1.get(var2) instanceof ICLabel var4) {
            ICStatement var5 = null;
            if (var2 == 0) {
               ICStatement var6 = CUtil.getParentBlock(this.m, var1);
               if (var6 instanceof ICIfStm) {
                  List var7 = CUtil.getPreviouslyExecutedStatements(this.m, var4);
                  if (var7 != null) {
                     for (ICStatement var9 : var7) {
                        if (!(var9 instanceof ICReturn) && !(var9 instanceof ICGoto)) {
                           var5 = null;
                           break;
                        }

                        var5 = var5 == null ? var9 : var5;
                     }
                  }
               }
            } else {
               var5 = var1.get(var2 - 1);
            }

            if (var5 instanceof ICReturn || var5 instanceof ICGoto) {
               int var13 = var2 + 1;

               boolean var14;
               for (var14 = true; var13 < var1.size(); var13++) {
                  ICStatement var15 = var1.get(var13);
                  if (!this.pC(var15, var4)) {
                     var14 = false;
                     break;
                  }

                  if (var15 instanceof ICLabel) {
                     this.pC(var1, var4, var2, var13 - 1, this.ef.createGoto((ICLabel)var15));
                     break;
                  }

                  Boolean var17 = this.pC(var15);
                  if (var17 == null) {
                     if (var15 instanceof ICBreak || var15 instanceof ICContinue) {
                        this.A(var1, var4, var2, var13, null);
                     }
                     break;
                  }

                  if (var17) {
                     this.pC(var1, var4, var2, var13, null);
                     break;
                  }
               }

               if (var14 && var13 == var1.size()) {
                  ICStatement var16 = CUtil.getFirstRealStatementEx(this.m, var1.getLast());
                  Object var18 = null;
                  if (var16 instanceof ICReturn) {
                     ICExpression var10 = ((ICReturn)var16).getExpression();
                     var18 = this.ef.createReturn(var10 == null ? null : var10.duplicate());
                  } else if (var16 instanceof ICGoto) {
                     var18 = this.ef.createGoto(((ICGoto)var16).getLabel());
                  } else if (var16 instanceof ICLabel) {
                     var18 = this.ef.createGoto((ICLabel)var16);
                  } else if (var16 == null && var1 == this.m.getBody()) {
                     var18 = this.ef.createReturn(null);
                  }

                  if (var18 != null) {
                     this.pC(var1, var4, var2, var13 - 1, (ICStatement)var18);
                  }
               }

               var2 = var13;
            }
         }
      }

      for (int var11 = 0; var11 < var1.size(); var11++) {
         ICStatement var12 = var1.get(var11);
         if (var12 instanceof ICGoto) {
            this.pC(var1, ((ICGoto)var12).getLabel(), var11, 0);
         }
      }

      return 0;
   }

   private void pC(ICBlock var1, ICLabel var2, int var3, int var4, ICStatement var5) {
      this.A.add(new ahl.Av(var1, var2, var3, var4, var5));
   }

   private void A(ICBlock var1, ICLabel var2, int var3, int var4, ICStatement var5) {
      this.kS.add(new ahl.Av(var1, var2, var3, var4, var5));
   }

   private void pC(ICBlock var1, ICLabel var2, int var3, int var4) {
      this.wS.add(new ahl.Av(var1, var2, var3, var4));
   }

   private boolean pC(ICStatement var1, ICLabel var2) {
      List var3 = var1.getSubElements();
      return this.pC(var3, var2, var1 instanceof ICGenericLoop, var1 instanceof ICGenericBreakable);
   }

   private boolean pC(List var1, ICLabel var2, boolean var3, boolean var4) {
      for (ICElement var6 : var1) {
         if (var6 instanceof ICStatement) {
            boolean var7 = var3 || var6 instanceof ICGenericLoop;
            boolean var8 = var4 || var6 instanceof ICGenericBreakable;
            if (!this.pC(var6.getSubElements(), var2, var7, var8)) {
               return false;
            }

            if (var6 instanceof ICGoto && ((ICGoto)var6).getLabel().equals(var2)) {
               return false;
            }

            if (var6 instanceof ICBreak) {
               if (((ICBreak)var6).getLabel() != null || !var3) {
                  return false;
               }
            } else if (var6 instanceof ICContinue && !var4 && (((ICContinue)var6).getLabel() != null || !var4)) {
               return false;
            }
         }
      }

      return true;
   }

   private Boolean pC(ICStatement var1) {
      if (var1 instanceof ICReturn || var1 instanceof ICGoto || var1 instanceof ICJumpFar) {
         return true;
      } else if (!(var1 instanceof ICAssignment)
         && !(var1 instanceof ICCall)
         && !(var1 instanceof ICDecl)
         && !(var1 instanceof ICLabel)
         && !(var1 instanceof ICCustomStatement)) {
         if (var1 instanceof ICConditionalStatement var2) {
            boolean var3 = var2.hasDefaultBlock();

            for (ICBlock var5 : var2.getBlocks()) {
               if (var5.isEmpty()) {
                  var3 = false;
               } else {
                  ICStatement var6 = var5.get(var5.size() - 1);
                  Boolean var7 = this.pC(var6);
                  if (var7 == null) {
                     return null;
                  }

                  if (!var7) {
                     var3 = false;
                  }
               }
            }

            return var3;
         } else {
            return !(var1 instanceof ICBreak) && !(var1 instanceof ICContinue) ? false : null;
         }
      } else {
         return false;
      }
   }

   private boolean pC(List var1, boolean var2) {
      if (var1.isEmpty()) {
         return false;
      } else {
         Iterator var3 = var1.iterator();

         ahl.Av var4;
         ICBlock var5;
         ahl.Av var11;
         while (true) {
            if (!var3.hasNext()) {
               return false;
            }

            var4 = (ahl.Av)var3.next();
            var5 = this.ef.createBlock();

            for (int var6 = var4.kS; var6 < var4.wS + 1; var6++) {
               var5.add(var4.pC.get(var6));
            }

            if (var4.UT != null) {
               var5.add(var4.UT);
            }

            var11 = this.pC(var5, var4.A);
            if (var11 != null) {
               if (var2) {
                  break;
               }

               ICStatement var7 = var4.pC.get(var4.wS);
               Object var8 = null;
               Object var9 = null;
               if (var7 instanceof ICControlBreaker && !(var7 instanceof ICGoto) && ((ICControlBreaker)var7).getLabel() == null) {
                  boolean var10 = var7 instanceof ICBreak;
                  var8 = var4.pC;

                  while (var8 != null && !(var8 instanceof ICGenericLoop) && (!var10 || !(var8 instanceof ICSwitchStm))) {
                     var8 = CUtil.getParentBlock(this.m, (ICStatement)var8);
                  }

                  var9 = var11.pC;

                  while (var9 != null && !(var9 instanceof ICGenericLoop) && (!var10 || !(var9 instanceof ICSwitchStm))) {
                     var9 = CUtil.getParentBlock(this.m, (ICStatement)var9);
                  }
               }

               if (var8 != null && var9 != null && var8 == var9) {
                  break;
               }
            }
         }

         Object[] var10000 = new Object[]{var4.A.getName()};
         int var12 = var11.kS;

         for (int var13 = var4.kS; var13 < var4.wS + 1; var13++) {
            var4.pC.remove(var4.kS);
            if (var4.pC == var11.pC && var4.kS < var12) {
               var12--;
            }
         }

         var11.pC.remove(var12);
         var11.pC.insertAll(var12, var5);
         return true;
      }
   }

   private ahl.Av pC(ICBlock var1, ICLabel var2) {
      ArrayList var3 = new ArrayList();

      for (ahl.Av var5 : this.wS) {
         if (var5.A.equals(var2)) {
            var3.add(var5);
         }
      }

      if (var3.isEmpty()) {
         return null;
      } else {
         int var9 = var3.size();

         for (ICElement var6 : var1) {
            if (var6 instanceof ICGoto && ((ICGoto)var6).getLabel() == var2 && !this.pC(var3, var6)) {
               return null;
            }

            for (ICElement var8 : var6.getSubElements()) {
               if (var8 instanceof ICGoto && ((ICGoto)var6).getLabel() == var2 && !this.pC(var3, var6)) {
                  return null;
               }
            }
         }

         if (var3.isEmpty()) {
            return null;
         } else {
            if (var9 == 1) {
               var1.remove(0);
            }

            return (ahl.Av)var3.get(0);
         }
      }
   }

   private boolean pC(List var1, ICElement var2) {
      int var3 = -1;

      for (int var4 = 0; var4 < var1.size(); var4++) {
         ahl.Av var5 = (ahl.Av)var1.get(var4);
         if (var5.pC.get(var5.kS) == var2) {
            if (var3 != -1) {
               return false;
            }

            var3 = var4;
         }
      }

      if (var3 != -1) {
         var1.remove(var3);
         return true;
      } else {
         return false;
      }
   }

   private static class Av {
      ICBlock pC;
      ICLabel A;
      int kS;
      int wS;
      ICStatement UT;

      public Av(ICBlock var1, ICLabel var2, int var3, int var4, ICStatement var5) {
         this.pC = var1;
         this.A = var2;
         this.kS = var3;
         this.wS = var4;
         this.UT = var5;
      }

      public Av(ICBlock var1, ICLabel var2, int var3, int var4) {
         this(var1, var2, var3, var4, null);
      }
   }
}
