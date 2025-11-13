package com.pnfsoftware.jeb.corei.parsers.asm.decompiler.explorer;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCompound;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aeg;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class nI {
   private static final StructuredLogger xK = aeg.q(nI.class);
   List q = new ArrayList();
   ICMethod RF;

   public void q(ICMethod var1) {
      this.RF = var1;
      this.q(var1.getBody(), null);
   }

   private void q(ICStatement var1, ICStatement var2) {
      if (var1 instanceof ICWhileStm) {
         this.q((ICWhileStm)var1, var2);
      } else if (var1 instanceof ICDoWhileStm) {
         this.q((ICDoWhileStm)var1, var2);
      } else if (var1 instanceof ICForStm) {
         this.q((ICForStm)var1, var2);
      } else if (var1 instanceof ICIfStm) {
         this.q((ICIfStm)var1, var2);
      } else if (var1 instanceof ICAssignment) {
         this.q((ICAssignment)var1, var2);
      } else if (var1 instanceof ICCall) {
         this.q((ICCall)var1, var2);
      } else if (var1 instanceof ICLabel) {
         this.q((ICLabel)var1, var2);
      } else if (var1 instanceof ICBlock) {
         this.q((ICBlock)var1, var2);
      } else if (var1 instanceof ICCompound) {
         for (ICBlock var4 : ((ICCompound)var1).getBlocks()) {
            this.q((ICStatement)var4, var2);
         }
      }
   }

   private void q(ICCall var1, ICStatement var2) {
      CU var3 = new CU();
      if (var1.getCandidateMethodAddresses() != null && var1.getCandidateMethodAddresses().size() == 1 && var1.getCallsite() instanceof ICConstantInteger) {
         ICConstantInteger var4 = (ICConstantInteger)var1.getCallsite();
         if (((ICMethod)var1.getCandidateMethods().get(0)).getPhysicalOffset() == var4.getValueAsLong()) {
            var3.q("resolved dynamic call");
         } else {
            var3.q("unresolved dynamic call");
         }
      }

      if (var1.getCandidateMethodAddresses() != null && var1.getCandidateMethodAddresses().size() > 1) {
         var3.q("unresolved dynamic call");
      }

      this.q.add(var3);
   }

   private void q(ICIfStm var1, ICStatement var2) {
      for (ICPredicate var4 : var1.getBranchPredicates()) {
         this.q(var4, "if");
      }

      for (ICBlock var6 : var1.getBlocks()) {
         this.q(var6, var2);
      }
   }

   private void q(ICPredicate var1, String var2) {
      int var3 = CUtil.countAllSubElements(var1);
      if (var3 >= 7) {
         EE var4 = new EE();
         var4.q(var3);
         var4.RF(var2 + ":" + var1.toString());
         this.q.add(var4);
      }
   }

   private void q(ICLabel var1, ICStatement var2) {
      tw var3 = new tw();
      if (var2 instanceof ICGoto) {
         var3.q("Trampoline!");
      }

      this.q.add(var3);
   }

   private void q(ICAssignment var1, ICStatement var2) {
      eo var3 = new eo();
      if (var1.getRight() instanceof ICOperation && ((ICOperation)var1.getRight()).getOperator() == this.RF.getOperatorFactory().get(COperatorType.COND)) {
         this.q((ICPredicate)((ICOperation)var1.getRight()).getFirstOperand(), "assign");
      }

      if (var1.getRight() instanceof ICCall) {
         this.q((ICCall)var1.getRight(), var2);
      }

      if (var1.isCombinedOperatorAssignment()
         && var1.getCombinedOperator() == this.RF.getOperatorFactory().get(COperatorType.ADD)
         && var1.getRight() instanceof ICConstantInteger) {
         BigInteger var4 = ((ICConstantInteger)var1.getRight()).getIntegerValue();
         if (var4.signum() == -1) {
            var3.q(true);
            var3.RF(var1.toString());
         }
      }

      this.q.add(var3);
   }

   private void q(ICForStm var1, ICStatement var2) {
      iZ var3 = new iZ();
      this.q.add(var3);
      this.q(var1.getPredicate(), "for");
      this.q(var1.getBody(), var2);
   }

   private void q(ICDoWhileStm var1, ICStatement var2) {
      oM var3 = new oM();
      if (var1.getPredicate().isLitteralTrue()) {
         var3.Dw = true;
      } else {
         this.q(var1.getPredicate(), "do-while");
      }

      if (var1.getBody().size() == 0) {
         var3.q = true;
         this.q.add(var3);
      } else {
         if (var2 instanceof ICLabel) {
            var3.xK = (ICLabel)var2;
         }

         if (var1.getBody().get(0) instanceof ICLabel) {
            var3.RF = (ICLabel)var1.getBody().get(0);
         }

         boolean var4 = false;

         for (int var5 = 0; var5 < var1.getBody().size(); var5++) {
            ICStatement var6 = var1.getBody().get(var5);
            if (var6 instanceof ICIfStm) {
               var3.Uv++;
            }

            if (var6 instanceof ICIfStm && !var4) {
               var4 = true;
            } else if (var4) {
               var3.gO++;
            } else {
               var3.oW++;
            }

            if (var5 < var1.getBody().size() - 1) {
               this.q(var6, var1.getBody().get(var5 + 1));
            } else {
               this.q(var6, var2);
            }
         }

         if (var3.Dw) {
            if (var3.xK != null) {
               ICLabel var9 = CUtil.getIfGotoTarget(var1.getBody().getLast());
               if (var9 != null && var9.equals(var3.xK)) {
                  var3.q("O: Class1 ok -- goto break (increase runcount?)");
               }
            }

            if (var1.getBody().getLast() instanceof ICIfStm) {
               ICIfStm var10 = (ICIfStm)var1.getBody().getLast();
               if (var10.getBlocks().size() == 1 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var3.q("O: Class1 ok -- break");
                  } else {
                     var3.q(Strings.ff("O: Class1 almost -- %s", ((ICBlock)var10.getBlocks().get(0)).get(0).getClass()));
                  }
               }

               boolean var7 = false;
               if (var10.getBlocks().size() == 2 && var10.hasDefaultBlock()) {
                  for (int var8 = 0; var8 < 2; var8++) {
                     if (((ICBlock)var10.getBlocks().get(var8)).size() == 1) {
                        if (var3.xK != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(var8)).get(0), var3.xK)) {
                           var7 = true;
                           var3.q("O: goto break (increase runcount?)");
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICBreak) {
                           var7 = true;
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICReturn) {
                           var3.q("O: C2 return (1)");
                        }
                     }
                  }
               }

               if (var10.getBlocks().size() > 2 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (var3.xK != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(0)).get(0), var3.xK)) {
                     var7 = true;
                     var3.q("O: goto break (increase runcount?)");
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var7 = true;
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICReturn) {
                     var3.q("O: C2 return (2)");
                  }
               }

               if (var7) {
                  if (var1.getBody().size() == 2) {
                     if (var1.getBody().get(0) instanceof ICAssignment) {
                        var3.q("O: Class2 ok -- assign");
                     } else {
                        var3.q(Strings.ff("O: Class2 almost -- A = %s", var1.getBody().get(0).getClass()));
                     }
                  }

                  if (var1.getBody().size() == 3) {
                     var3.q(Strings.ff("O: Class2 almost -- A with two insn = %s %s", var1.getBody().get(0).getClass(), var1.getBody().get(1).getClass()));
                  }

                  if (var1.getBody().size() == 1) {
                     var3.q("O: Class2 ok -- empty A");
                  }
               }
            } else {
               var3.q(Strings.ff("O: last is not if -- %d stmts", var1.getBody().size()));
            }
         }

         this.q.add(var3);
      }
   }

   private void q(ICWhileStm var1, ICStatement var2) {
      vn var3 = new vn();
      if (var1.getPredicate().isLitteralTrue()) {
         var3.Dw = true;
      } else {
         this.q(var1.getPredicate(), "while");
      }

      if (var1.getBody().size() == 0) {
         var3.q = true;
         this.q.add(var3);
      } else {
         if (var2 instanceof ICLabel) {
            var3.xK = (ICLabel)var2;
         }

         if (var1.getBody().get(0) instanceof ICLabel) {
            var3.RF = (ICLabel)var1.getBody().get(0);
         }

         boolean var4 = false;

         for (int var5 = 0; var5 < var1.getBody().size(); var5++) {
            ICStatement var6 = var1.getBody().get(var5);
            if (var6 instanceof ICIfStm) {
               var3.Uv++;
            }

            if (var6 instanceof ICIfStm && !var4) {
               var4 = true;
            } else if (var4) {
               var3.gO++;
            } else {
               var3.oW++;
            }

            if (var5 < var1.getBody().size() - 1) {
               this.q(var6, var1.getBody().get(var5 + 1));
            } else {
               this.q(var6, var2);
            }
         }

         if (var3.Dw) {
            if (var3.xK != null) {
               ICLabel var9 = CUtil.getIfGotoTarget(var1.getBody().getLast());
               if (var9 != null && var9.equals(var3.xK)) {
                  var3.q("O: Class1 ok -- goto break (increase runcount?)");
               }
            }

            if (var1.getBody().getLast() instanceof ICIfStm) {
               ICIfStm var10 = (ICIfStm)var1.getBody().getLast();
               if (var10.getBlocks().size() == 1 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var3.q("O: Class1 ok -- break");
                  } else {
                     var3.q(Strings.ff("O: Class1 almost -- %s", ((ICBlock)var10.getBlocks().get(0)).get(0).getClass()));
                  }
               }

               boolean var7 = false;
               if (var10.getBlocks().size() == 2 && var10.hasDefaultBlock()) {
                  for (int var8 = 0; var8 < 2; var8++) {
                     if (((ICBlock)var10.getBlocks().get(var8)).size() == 1) {
                        if (var3.xK != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(var8)).get(0), var3.xK)) {
                           var7 = true;
                           var3.q("O: goto break (increase runcount?)");
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICBreak) {
                           var7 = true;
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICReturn) {
                           var3.q("O: C2 return (1)");
                        }
                     }
                  }
               }

               if (var10.getBlocks().size() > 2 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (var3.xK != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(0)).get(0), var3.xK)) {
                     var7 = true;
                     var3.q("O: goto break (increase runcount?)");
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var7 = true;
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICReturn) {
                     var3.q("O: C2 return (2)");
                  }
               }

               if (var7) {
                  if (var1.getBody().size() == 2) {
                     if (var1.getBody().get(0) instanceof ICAssignment) {
                        var3.q("O: Class2 ok -- assign");
                     } else {
                        var3.q(Strings.ff("O: Class2 almost -- A = %s", var1.getBody().get(0).getClass()));
                     }
                  }

                  if (var1.getBody().size() == 1) {
                     var3.q("O: Class2 ok -- empty A");
                  }
               }
            } else {
               var3.q(Strings.ff("O: last is not if -- %d stmts", var1.getBody().size()));
            }
         }

         this.q.add(var3);
      }
   }

   private void q(ICBlock var1, ICStatement var2) {
      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var3 < var1.size() - 1) {
            this.q(var4, var1.get(var3 + 1));
         } else {
            this.q(var4, var2);
         }
      }
   }

   public List q() {
      return this.q;
   }

   public void RF() {
      this.q = new ArrayList();
   }
}
