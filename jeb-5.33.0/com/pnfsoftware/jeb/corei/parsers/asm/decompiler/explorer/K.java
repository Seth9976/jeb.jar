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
import com.pnfsoftware.jebglobal.aco;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class K {
   private static final StructuredLogger kS = aco.pC(K.class);
   List pC = new ArrayList();
   ICMethod A;

   public void pC(ICMethod var1) {
      this.A = var1;
      this.pC(var1.getBody(), null);
   }

   private void pC(ICStatement var1, ICStatement var2) {
      if (var1 instanceof ICWhileStm) {
         this.pC((ICWhileStm)var1, var2);
      } else if (var1 instanceof ICDoWhileStm) {
         this.pC((ICDoWhileStm)var1, var2);
      } else if (var1 instanceof ICForStm) {
         this.pC((ICForStm)var1, var2);
      } else if (var1 instanceof ICIfStm) {
         this.pC((ICIfStm)var1, var2);
      } else if (var1 instanceof ICAssignment) {
         this.pC((ICAssignment)var1, var2);
      } else if (var1 instanceof ICCall) {
         this.pC((ICCall)var1, var2);
      } else if (var1 instanceof ICLabel) {
         this.pC((ICLabel)var1, var2);
      } else if (var1 instanceof ICBlock) {
         this.pC((ICBlock)var1, var2);
      } else if (var1 instanceof ICCompound) {
         for (ICBlock var4 : ((ICCompound)var1).getBlocks()) {
            this.pC((ICStatement)var4, var2);
         }
      }
   }

   private void pC(ICCall var1, ICStatement var2) {
      Sv var3 = new Sv();
      if (var1.getCandidateMethodAddresses() != null && var1.getCandidateMethodAddresses().size() == 1 && var1.getCallsite() instanceof ICConstantInteger) {
         ICConstantInteger var4 = (ICConstantInteger)var1.getCallsite();
         if (((ICMethod)var1.getCandidateMethods().get(0)).getPhysicalOffset() == var4.getValueAsLong()) {
            var3.pC("resolved dynamic call");
         } else {
            var3.pC("unresolved dynamic call");
         }
      }

      if (var1.getCandidateMethodAddresses() != null && var1.getCandidateMethodAddresses().size() > 1) {
         var3.pC("unresolved dynamic call");
      }

      this.pC.add(var3);
   }

   private void pC(ICIfStm var1, ICStatement var2) {
      for (ICPredicate var4 : var1.getBranchPredicates()) {
         this.pC(var4, "if");
      }

      for (ICBlock var6 : var1.getBlocks()) {
         this.pC(var6, var2);
      }
   }

   private void pC(ICPredicate var1, String var2) {
      int var3 = CUtil.countAllSubElements(var1);
      if (var3 >= 7) {
         zp var4 = new zp();
         var4.pC(var3);
         var4.pC(var2 + ":" + var1.toString());
         this.pC.add(var4);
      }
   }

   private void pC(ICLabel var1, ICStatement var2) {
      rQ var3 = new rQ();
      if (var2 instanceof ICGoto) {
         var3.pC("Trampoline!");
      }

      this.pC.add(var3);
   }

   private void pC(ICAssignment var1, ICStatement var2) {
      Av var3 = new Av();
      if (var1.getRight() instanceof ICOperation && ((ICOperation)var1.getRight()).getOperator() == this.A.getOperatorFactory().get(COperatorType.COND)) {
         this.pC((ICPredicate)((ICOperation)var1.getRight()).getFirstOperand(), "assign");
      }

      if (var1.getRight() instanceof ICCall) {
         this.pC((ICCall)var1.getRight(), var2);
      }

      if (var1.isCombinedOperatorAssignment()
         && var1.getCombinedOperator() == this.A.getOperatorFactory().get(COperatorType.ADD)
         && var1.getRight() instanceof ICConstantInteger) {
         BigInteger var4 = ((ICConstantInteger)var1.getRight()).getIntegerValue();
         if (var4.signum() == -1) {
            var3.pC(true);
            var3.pC(var1.toString());
         }
      }

      this.pC.add(var3);
   }

   private void pC(ICForStm var1, ICStatement var2) {
      DH var3 = new DH();
      this.pC.add(var3);
      this.pC(var1.getPredicate(), "for");
      this.pC(var1.getBody(), var2);
   }

   private void pC(ICDoWhileStm var1, ICStatement var2) {
      bO var3 = new bO();
      if (var1.getPredicate().isLitteralTrue()) {
         var3.wS = true;
      } else {
         this.pC(var1.getPredicate(), "do-while");
      }

      if (var1.getBody().size() == 0) {
         var3.pC = true;
         this.pC.add(var3);
      } else {
         if (var2 instanceof ICLabel) {
            var3.kS = (ICLabel)var2;
         }

         if (var1.getBody().get(0) instanceof ICLabel) {
            var3.A = (ICLabel)var1.getBody().get(0);
         }

         boolean var4 = false;

         for (int var5 = 0; var5 < var1.getBody().size(); var5++) {
            ICStatement var6 = var1.getBody().get(var5);
            if (var6 instanceof ICIfStm) {
               var3.UT++;
            }

            if (var6 instanceof ICIfStm && !var4) {
               var4 = true;
            } else if (var4) {
               var3.sY++;
            } else {
               var3.E++;
            }

            if (var5 < var1.getBody().size() - 1) {
               this.pC(var6, var1.getBody().get(var5 + 1));
            } else {
               this.pC(var6, var2);
            }
         }

         if (var3.wS) {
            if (var3.kS != null) {
               ICLabel var9 = CUtil.getIfGotoTarget(var1.getBody().getLast());
               if (var9 != null && var9.equals(var3.kS)) {
                  var3.pC("O: Class1 ok -- goto break (increase runcount?)");
               }
            }

            if (var1.getBody().getLast() instanceof ICIfStm) {
               ICIfStm var10 = (ICIfStm)var1.getBody().getLast();
               if (var10.getBlocks().size() == 1 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var3.pC("O: Class1 ok -- break");
                  } else {
                     var3.pC(Strings.ff("O: Class1 almost -- %s", ((ICBlock)var10.getBlocks().get(0)).get(0).getClass()));
                  }
               }

               boolean var7 = false;
               if (var10.getBlocks().size() == 2 && var10.hasDefaultBlock()) {
                  for (int var8 = 0; var8 < 2; var8++) {
                     if (((ICBlock)var10.getBlocks().get(var8)).size() == 1) {
                        if (var3.kS != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(var8)).get(0), var3.kS)) {
                           var7 = true;
                           var3.pC("O: goto break (increase runcount?)");
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICBreak) {
                           var7 = true;
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICReturn) {
                           var3.pC("O: C2 return (1)");
                        }
                     }
                  }
               }

               if (var10.getBlocks().size() > 2 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (var3.kS != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(0)).get(0), var3.kS)) {
                     var7 = true;
                     var3.pC("O: goto break (increase runcount?)");
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var7 = true;
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICReturn) {
                     var3.pC("O: C2 return (2)");
                  }
               }

               if (var7) {
                  if (var1.getBody().size() == 2) {
                     if (var1.getBody().get(0) instanceof ICAssignment) {
                        var3.pC("O: Class2 ok -- assign");
                     } else {
                        var3.pC(Strings.ff("O: Class2 almost -- A = %s", var1.getBody().get(0).getClass()));
                     }
                  }

                  if (var1.getBody().size() == 3) {
                     var3.pC(Strings.ff("O: Class2 almost -- A with two insn = %s %s", var1.getBody().get(0).getClass(), var1.getBody().get(1).getClass()));
                  }

                  if (var1.getBody().size() == 1) {
                     var3.pC("O: Class2 ok -- empty A");
                  }
               }
            } else {
               var3.pC(Strings.ff("O: last is not if -- %d stmts", var1.getBody().size()));
            }
         }

         this.pC.add(var3);
      }
   }

   private void pC(ICWhileStm var1, ICStatement var2) {
      yt var3 = new yt();
      if (var1.getPredicate().isLitteralTrue()) {
         var3.wS = true;
      } else {
         this.pC(var1.getPredicate(), "while");
      }

      if (var1.getBody().size() == 0) {
         var3.pC = true;
         this.pC.add(var3);
      } else {
         if (var2 instanceof ICLabel) {
            var3.kS = (ICLabel)var2;
         }

         if (var1.getBody().get(0) instanceof ICLabel) {
            var3.A = (ICLabel)var1.getBody().get(0);
         }

         boolean var4 = false;

         for (int var5 = 0; var5 < var1.getBody().size(); var5++) {
            ICStatement var6 = var1.getBody().get(var5);
            if (var6 instanceof ICIfStm) {
               var3.UT++;
            }

            if (var6 instanceof ICIfStm && !var4) {
               var4 = true;
            } else if (var4) {
               var3.sY++;
            } else {
               var3.E++;
            }

            if (var5 < var1.getBody().size() - 1) {
               this.pC(var6, var1.getBody().get(var5 + 1));
            } else {
               this.pC(var6, var2);
            }
         }

         if (var3.wS) {
            if (var3.kS != null) {
               ICLabel var9 = CUtil.getIfGotoTarget(var1.getBody().getLast());
               if (var9 != null && var9.equals(var3.kS)) {
                  var3.pC("O: Class1 ok -- goto break (increase runcount?)");
               }
            }

            if (var1.getBody().getLast() instanceof ICIfStm) {
               ICIfStm var10 = (ICIfStm)var1.getBody().getLast();
               if (var10.getBlocks().size() == 1 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var3.pC("O: Class1 ok -- break");
                  } else {
                     var3.pC(Strings.ff("O: Class1 almost -- %s", ((ICBlock)var10.getBlocks().get(0)).get(0).getClass()));
                  }
               }

               boolean var7 = false;
               if (var10.getBlocks().size() == 2 && var10.hasDefaultBlock()) {
                  for (int var8 = 0; var8 < 2; var8++) {
                     if (((ICBlock)var10.getBlocks().get(var8)).size() == 1) {
                        if (var3.kS != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(var8)).get(0), var3.kS)) {
                           var7 = true;
                           var3.pC("O: goto break (increase runcount?)");
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICBreak) {
                           var7 = true;
                        }

                        if (((ICBlock)var10.getBlocks().get(var8)).get(0) instanceof ICReturn) {
                           var3.pC("O: C2 return (1)");
                        }
                     }
                  }
               }

               if (var10.getBlocks().size() > 2 && ((ICBlock)var10.getBlocks().get(0)).size() == 1) {
                  if (var3.kS != null && CUtil.isGotoTo(((ICBlock)var10.getBlocks().get(0)).get(0), var3.kS)) {
                     var7 = true;
                     var3.pC("O: goto break (increase runcount?)");
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICBreak) {
                     var7 = true;
                  }

                  if (((ICBlock)var10.getBlocks().get(0)).get(0) instanceof ICReturn) {
                     var3.pC("O: C2 return (2)");
                  }
               }

               if (var7) {
                  if (var1.getBody().size() == 2) {
                     if (var1.getBody().get(0) instanceof ICAssignment) {
                        var3.pC("O: Class2 ok -- assign");
                     } else {
                        var3.pC(Strings.ff("O: Class2 almost -- A = %s", var1.getBody().get(0).getClass()));
                     }
                  }

                  if (var1.getBody().size() == 1) {
                     var3.pC("O: Class2 ok -- empty A");
                  }
               }
            } else {
               var3.pC(Strings.ff("O: last is not if -- %d stmts", var1.getBody().size()));
            }
         }

         this.pC.add(var3);
      }
   }

   private void pC(ICBlock var1, ICStatement var2) {
      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var3 < var1.size() - 1) {
            this.pC(var4, var1.get(var3 + 1));
         } else {
            this.pC(var4, var2);
         }
      }
   }

   public List pC() {
      return this.pC;
   }

   public void A() {
      this.pC = new ArrayList();
   }
}
