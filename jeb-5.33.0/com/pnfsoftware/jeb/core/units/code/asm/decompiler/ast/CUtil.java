package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CUtil {
   public static ICElement findParent(ICElement var0, ICElement var1) {
      List var2 = var0.getSubElements();

      for (ICElement var4 : var2) {
         if (var4 == var1) {
            return var0;
         }
      }

      for (ICElement var7 : var2) {
         ICElement var5 = findParent(var7, var1);
         if (var5 != null) {
            return var5;
         }
      }

      return null;
   }

   public static boolean isClassMethodField(ICElement var0) {
      return var0 instanceof ICClass || var0 instanceof ICMethod || var0 instanceof ICField;
   }

   public static boolean isIntegerConstant(ICExpression var0) {
      return var0 instanceof ICConstantInteger;
   }

   public static boolean isIntegerValue(ICExpression var0, long var1) {
      if (!isIntegerConstant(var0)) {
         return false;
      } else {
         Long var3 = getConstantAsLong((ICConstantInteger)var0);
         return var3 != null && var3 == var1;
      }
   }

   public static Long getConstantAsLong(ICConstant var0) {
      Object var1 = var0.getValue();
      if (var1 instanceof Integer) {
         return ((Integer)var1).longValue();
      } else {
         return var1 instanceof Long ? (Long)var1 : null;
      }
   }

   public static boolean hasNoSideEffects(ICElement var0) {
      if (!(var0 instanceof ICCall) && !(var0 instanceof ICAssignment)) {
         for (ICElement var2 : var0.getSubElements()) {
            if (!isClassMethodField(var2)) {
               boolean var3 = hasNoSideEffects(var2);
               if (!var3) {
                  return false;
               }
            }
         }

         return true;
      } else {
         return false;
      }
   }

   public static boolean hasNoCall(ICElement var0) {
      if (var0 instanceof ICCall) {
         return false;
      } else {
         for (ICElement var2 : var0.getSubElements()) {
            if (!isClassMethodField(var2)) {
               boolean var3 = hasNoCall(var2);
               if (!var3) {
                  return false;
               }
            }
         }

         return true;
      }
   }

   public static ICLabel getIfGotoTarget(ICStatement var0) {
      if (!(var0 instanceof ICIfStm var1)) {
         return null;
      } else if (var1.size() != 1) {
         return null;
      } else {
         ICBlock var2 = var1.getBranchBody(0);
         return var2.size() == 1 && var2.get(0) instanceof ICGoto ? ((ICGoto)var2.get(0)).getLabel() : null;
      }
   }

   public static boolean isIfBranch(ICStatement var0) {
      if (!(var0 instanceof ICIfStm var1)) {
         return false;
      } else if (var1.size() != 1) {
         return false;
      } else {
         ICBlock var2 = var1.getBranchBody(0);
         if (var2.size() != 1) {
            return false;
         } else {
            ICStatement var3 = var2.get(0);
            return var3 instanceof ICGoto || var3 instanceof ICBreak || var3 instanceof ICContinue || var3 instanceof ICReturn || var3 instanceof ICThrow;
         }
      }
   }

   public static boolean hasEmptyBranch(ICIfStm var0) {
      for (ICBlock var2 : var0.getBlocks()) {
         if (var2.size() == 0) {
            return true;
         }
      }

      return false;
   }

   public static boolean isContainingLabel(ICStatement var0, ICLabel var1) {
      if (var0 instanceof ICLabel && ((ICLabel)var0).equals(var1)) {
         return true;
      } else {
         if (var0 instanceof ICCompound) {
            for (ICBlock var3 : ((ICCompound)var0).getBlocks()) {
               for (ICStatement var5 : var3) {
                  if (isContainingLabel(var5, var1)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public static boolean isGotoTo(ICStatement var0, ICLabel var1) {
      return var0 instanceof ICGoto && ((ICGoto)var0).getLabel() == var1;
   }

   public static ICLabel getGotoLabel(ICStatement var0) {
      return var0 instanceof ICGoto ? ((ICGoto)var0).getLabel() : null;
   }

   public static boolean isBreakTo(ICStatement var0, ICLabel var1) {
      return var0 instanceof ICBreak && ((ICBreak)var0).getLabel() == var1;
   }

   public static boolean isPlainBreak(ICStatement var0) {
      return var0 instanceof ICBreak && ((ICBreak)var0).getLabel() == null;
   }

   public static boolean isIfElse(ICStatement var0) {
      return var0 instanceof ICIfStm var1 && var1.size() == 2 && var1.hasDefaultBlock();
   }

   public static boolean flipIfElse(ICStatement var0, ICOperatorFactory var1) {
      if (var0 instanceof ICIfStm var2 && var2.size() == 2 && var2.hasDefaultBlock()) {
         ICBlock var3 = var2.getBranchBody(0);
         ICBlock var4 = var2.getDefaultBlock();
         var2.getBranchPredicate(0).reverse(var1);
         var2.setBranchBody(0, var4);
         var2.setDefaultBlock(var3);
         return true;
      } else {
         return false;
      }
   }

   public static boolean isIfNoElse(ICStatement var0) {
      return var0 instanceof ICIfStm var1 ? !var1.hasDefaultBlock() : false;
   }

   public static boolean isWhileTrue(ICStatement var0) {
      return var0 instanceof ICWhileStm ? ((ICWhileStm)var0).getPredicate().isLitteralTrue() : false;
   }

   public static boolean isDoWhileTrue(ICStatement var0) {
      return var0 instanceof ICDoWhileStm ? ((ICDoWhileStm)var0).getPredicate().isLitteralTrue() : false;
   }

   public static int countAllSubElements(ICElement var0) {
      int var1 = 0;

      for (ICElement var3 : var0.getSubElements()) {
         if (isClassMethodField(var3)) {
            var1++;
         } else {
            var1 += countAllSubElements(var3);
         }
      }

      return var1 + 1;
   }

   public static ICStatement getFirstRealStatement(ICBlock var0, int var1) {
      if (var1 >= var0.size()) {
         return null;
      } else {
         ICStatement var2 = var0.get(var1);
         if (!(var2 instanceof ICCompound)) {
            return var2;
         } else if (var2 instanceof ICDoWhileStm) {
            ICStatement var5 = getFirstRealStatement(((ICDoWhileStm)var2).getBody(), 0);
            return var5 == null ? var2 : var5;
         } else if (var2 instanceof ICWhileStm) {
            if (!((ICWhileStm)var2).getPredicate().isLitteralTrue()) {
               return var2;
            } else {
               ICStatement var4 = getFirstRealStatement(((ICWhileStm)var2).getBody(), 0);
               return var4 == null ? var2 : var4;
            }
         } else if (var2 instanceof ICIfStm) {
            if (!((ICIfStm)var2).getBranchPredicate(0).isLitteralTrue()) {
               return var2;
            } else {
               ICStatement var3 = getFirstRealStatement(((ICIfStm)var2).getBranchBody(0), 0);
               return var3 == null ? var2 : var3;
            }
         } else {
            return var2;
         }
      }
   }

   public static ICStatement getFirstRealStatementEx(ICMethod var0, ICStatement var1) {
      Boolean[] var2 = new Boolean[]{Boolean.FALSE, null};
      return findFirstStatementAfter(var0.getBody(), var1, var2);
   }

   private static ICStatement findFirstStatementAfter(ICBlock var0, ICStatement var1, Boolean[] var2) {
      for (int var3 = 0; var3 < var0.size(); var3++) {
         ICStatement var4 = var0.get(var3);
         if (Boolean.FALSE.equals(var2[1])) {
            return null;
         }

         if (var2[0]) {
            return getFirstRealStatement(var0, var3);
         }

         if (var4 == var1) {
            var2[0] = true;
         } else if (var4 instanceof ICCompound) {
            for (ICBlock var6 : ((ICCompound)var4).getBlocks()) {
               ICStatement var7 = findFirstStatementAfter(var6, var1, var2);
               if (var7 != null) {
                  return var7;
               }

               if (var2[0]) {
                  if (var2[1] == null && !(var4 instanceof ICConditionalStatement)) {
                     var2[1] = Boolean.FALSE;
                  }
                  break;
               }
            }
         }
      }

      return null;
   }

   public static boolean canFallthrough(ICStatement var0, ICStatement var1, boolean var2) {
      if (var0 == null || var1 == null) {
         throw new InvalidParameterException();
      } else if (containsLabel(var1)) {
         return true;
      } else if (!(var0 instanceof ICGoto)
         && (var2 || !(var0 instanceof ICBreak))
         && !(var0 instanceof ICContinue)
         && !(var0 instanceof ICJumpFar)
         && !(var0 instanceof ICReturn)
         && !(var0 instanceof ICThrow)) {
         if (var0 instanceof ICBlock var3 && !var3.isEmpty()) {
            return canFallthrough(var3.getLast(), var1, var2);
         } else if (var0 instanceof ICConditionalStatement var7 && var7.hasDefaultBlock()) {
            boolean var4 = true;

            for (ICBlock var6 : var7.getBlocks()) {
               if (var6.isEmpty()) {
                  var4 = false;
                  break;
               }

               if (!var6.isEmpty() && canFallthrough(var6, var1, var2 || var0 instanceof ICSwitchStm)) {
                  var4 = false;
                  break;
               }
            }

            return !var4;
         } else {
            return var0 instanceof ICGenericLoop ? true : true;
         }
      } else {
         return false;
      }
   }

   public static List getPreviouslyExecutedStatements(ICMethod var0, ICStatement var1) {
      ICStatement var2 = getParentBlock(var0, var1);
      if (var2 == null) {
         return null;
      } else if (var2 instanceof ICBlock var3) {
         return getPreviouslyExecutedStatements(var0, var3, var1);
      } else {
         return var2 instanceof ICIfStm ? getPreviouslyExecutedStatements(var0, var2) : null;
      }
   }

   private static List getPreviouslyExecutedStatements(ICMethod var0, ICBlock var1, ICStatement var2) {
      for (int var3 = 0; var3 < var1.size(); var3++) {
         ICStatement var4 = var1.get(var3);
         if (var4 == var2) {
            if (var3 == 0) {
               return getPreviouslyExecutedStatements(var0, var1);
            }

            return getLastInstructions(var1.get(var3 - 1));
         }
      }

      return null;
   }

   private static List getLastInstructions(ICStatement var0) {
      if (!(var0 instanceof ICCompound)) {
         return Arrays.asList(var0);
      } else if (!(var0 instanceof ICIfStm var1)) {
         return null;
      } else if (!var1.hasDefaultBlock()) {
         return null;
      } else {
         ArrayList var2 = new ArrayList();

         for (int var3 = 0; var3 < var1.sizeWithoutDefault(); var3++) {
            ICBlock var4 = var1.getBranchBody(var3);
            if (var4 == null || var4.isEmpty()) {
               return null;
            }

            List var5 = getLastInstructions(var4.getLast());
            if (var5 == null) {
               return null;
            }

            var2.addAll(var5);
         }

         if (var1.getDefaultBlock().isEmpty()) {
            return null;
         } else {
            List var6 = getLastInstructions(var1.getDefaultBlock().getLast());
            if (var6 == null) {
               return null;
            } else {
               var2.addAll(var6);
               return var2;
            }
         }
      }
   }

   public static ICStatement getParentBlock(ICMethod var0, ICStatement var1) {
      return getParentBlockInner(var0.getBody(), var1, null) instanceof ICStatement var3 ? var3 : null;
   }

   private static ICElement getParentBlockInner(ICCompound var0, ICStatement var1, ICElement var2) {
      if (var0 == var1) {
         return var2;
      } else {
         for (ICElement var4 : var0.getSubElements()) {
            if (var4 == var1) {
               return var0;
            }

            if (var4 instanceof ICCompound var5) {
               ICElement var6 = getParentBlockInner(var5, var1, var0);
               if (var6 != null) {
                  return var6;
               }
            }
         }

         return null;
      }
   }

   public static CUtil.BreakFlowStatus isBreakingFlow(ICStatement var0) {
      return getBreakingFlowResult(var0).getStatus();
   }

   public static CUtil.BreakFlowResult getBreakingFlowResult(ICStatement var0) {
      if (!(var0 instanceof ICReturn)
         && !(var0 instanceof ICThrow)
         && !(var0 instanceof ICGoto)
         && !(var0 instanceof ICContinue)
         && !(var0 instanceof ICJumpFar)) {
         if (!(var0 instanceof ICAssignment) && !(var0 instanceof ICLabel) && !(var0 instanceof ICCustomStatement) && !(var0 instanceof ICDecl)) {
            if (var0 instanceof ICBreak var1) {
               return var1.getLabel() == null ? CUtil.BreakFlowResult.BREAK : CUtil.BreakFlowResult.TRUE;
            } else if (var0 instanceof ICConditionalStatement var2) {
               boolean var10 = false;
               boolean var11 = false;
               if (!var2.hasDefaultBlock()) {
                  var11 = true;
               }

               boolean var6 = true;

               for (ICBlock var8 : var2.getBlocks()) {
                  CUtil.BreakFlowResult var9 = getBreakingFlowResult(var8);
                  switch (var9.getStatus()) {
                     case TRUE:
                        var10 = true;
                        var6 &= var9.isLastInstruction();
                        break;
                     case FALSE:
                        var11 = true;
                        var6 = false;
                        break;
                     case BOTH:
                        var10 = true;
                        var11 = true;
                        var6 &= var9.isLastInstruction();
                        break;
                     case UNKNOWN:
                        return var9;
                     case BREAK:
                        if (var0 instanceof ICSwitchStm) {
                           var11 = true;
                        } else {
                           var10 = true;
                        }

                        var6 &= var9.isLastInstruction();
                  }
               }

               if (var10 && var11) {
                  return new CUtil.BreakFlowResult(CUtil.BreakFlowStatus.BOTH, var6);
               } else {
                  return var10 ? new CUtil.BreakFlowResult(CUtil.BreakFlowStatus.TRUE, var6) : CUtil.BreakFlowResult.FALSE;
               }
            } else if (!(var0 instanceof ICBlock var3)) {
               return CUtil.BreakFlowResult.UNKNOWN;
            } else if (var3.isEmpty()) {
               return CUtil.BreakFlowResult.FALSE;
            } else {
               for (int var4 = 0; var4 < var3.size(); var4++) {
                  CUtil.BreakFlowResult var5 = getBreakingFlowResult(var3.get(var4));
                  if (var5.getStatus() == CUtil.BreakFlowStatus.TRUE) {
                     return new CUtil.BreakFlowResult(var5.getStatus(), var5.isLastInstruction() && var4 == var3.size() - 1);
                  }

                  if (var5.getStatus() != CUtil.BreakFlowStatus.FALSE) {
                     return new CUtil.BreakFlowResult(var5.getStatus(), var5.isLastInstruction() && var4 == var3.size() - 1);
                  }
               }

               return CUtil.BreakFlowResult.FALSE;
            }
         } else {
            return CUtil.BreakFlowResult.FALSE;
         }
      } else {
         return CUtil.BreakFlowResult.TRUE;
      }
   }

   public static ICOperation lt(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.LT), var1, var2);
   }

   public static ICOperation le(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.LE), var1, var2);
   }

   public static ICOperation gt(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.GT), var1, var2);
   }

   public static ICOperation ge(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.GE), var1, var2);
   }

   public static ICOperation eq(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.EQ), var1, var2);
   }

   public static ICOperation ne(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.NE), var1, var2);
   }

   public static ICOperation andL(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.LOG_AND), var1, var2);
   }

   public static ICOperation orL(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.LOG_OR), var1, var2);
   }

   public static ICExpression notL(ICMethod var0, ICExpression var1) {
      return (ICExpression)(var1 instanceof ICOperation && ((ICOperation)var1).checkOperatorType(COperatorType.LOG_NOT)
         ? ((ICOperation)var1).getFirstOperand()
         : var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.LOG_NOT), var1));
   }

   public static ICExpression notLDeepReplace(ICMethod var0, ICExpression var1) {
      ICExpression var2 = notL(var0, var1);
      if (var2 instanceof ICOperation var3) {
         ICExpression var4 = resolveNotOperation(var0, var3, null);
         if (var4 != null) {
            return var4;
         }
      }

      return var2;
   }

   public static ICExpression resolveNotOperation(ICMethod var0, ICOperation var1, ICElement var2) {
      if (var1.getOperatorType() == COperatorType.LOG_NOT && var1.getFirstOperand() instanceof ICOperation var3) {
         ICOperation var5 = var3.duplicate();
         if (var5.reverse(var0.getGlobalContext().getOperatorFactory())) {
            if (var2 == null || var2.replaceSubElement(var1, var5)) {
               return var5;
            }
         } else if (var5.getOperatorType() == COperatorType.LOG_AND) {
            var5 = orL(var0, notL(var0, var5.getFirstOperand()), notL(var0, var5.getSecondOperand()));
            if (var2 == null || var2.replaceSubElement(var1, var5)) {
               resolveNotOperationG(var0, var5.getFirstOperand(), var5);
               resolveNotOperationG(var0, var5.getSecondOperand(), var5);
               return var5;
            }
         } else if (var5.getOperatorType() == COperatorType.LOG_OR) {
            var5 = andL(var0, notL(var0, var5.getFirstOperand()), notL(var0, var5.getSecondOperand()));
            if (var2 == null || var2.replaceSubElement(var1, var5)) {
               resolveNotOperationG(var0, var5.getFirstOperand(), var5);
               resolveNotOperationG(var0, var5.getSecondOperand(), var5);
               return var5;
            }
         }
      }

      return null;
   }

   private static ICExpression resolveNotOperationG(ICMethod var0, ICExpression var1, ICElement var2) {
      return var1 instanceof ICOperation var3 ? resolveNotOperation(var0, var3, var2) : null;
   }

   public static ICOperation andB(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.AND), var1, var2);
   }

   public static ICOperation orB(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.OR), var1, var2);
   }

   public static ICOperation xorB(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.XOR), var1, var2);
   }

   public static ICExpression notB(ICMethod var0, ICExpression var1) {
      return (ICExpression)(var1 instanceof ICOperation && ((ICOperation)var1).checkOperatorType(COperatorType.NOT)
         ? ((ICOperation)var1).getFirstOperand()
         : var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.NOT), var1));
   }

   public static ICOperation add(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.ADD), var1, var2);
   }

   public static ICOperation add(ICMethod var0, ICExpression var1, ICExpression var2, ICExpression var3) {
      return add(var0, add(var0, var1, var2), var3);
   }

   public static ICOperation sub(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.SUB), var1, var2);
   }

   public static ICOperation mul(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.MUL), var1, var2);
   }

   public static ICOperation div(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.DIV), var1, var2);
   }

   public static ICOperation rem(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.REM), var1, var2);
   }

   public static ICOperation shl(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.SHL), var1, var2);
   }

   public static ICOperation shr(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.SHR), var1, var2);
   }

   public static ICOperation ushr(ICMethod var0, ICExpression var1, ICExpression var2) {
      return var0.getElementFactory().createOperation(var0.getOperatorFactory().get(COperatorType.USHR), var1, var2);
   }

   public static boolean isOperation(ICExpression var0, COperatorType... var1) {
      return getOperation(var0, var1) != null;
   }

   public static COperatorType getOperation(ICExpression var0, COperatorType... var1) {
      return var0 instanceof ICOperation ? getOperation((ICOperation)var0, var1) : null;
   }

   public static COperatorType getOperation(ICOperation var0, COperatorType... var1) {
      COperatorType var2 = var0.getOperatorType();

      for (COperatorType var6 : var1) {
         if (var6 == var2) {
            return var2;
         }
      }

      return null;
   }

   public static int replaceSubElementRecurse(ICElement var0, ICExpression var1, ICExpression var2) {
      int var3 = 0;
      if (var2 instanceof ICConstant && var0 instanceof ICInstanceField) {
         return 0;
      } else {
         for (ICElement var6 : var0.getSubElements()) {
            if (!isClassMethodField(var6)) {
               if (var6.equals(var1)) {
                  if (var0.replaceSubElement(var6, var2)) {
                     var3++;
                  }
               } else {
                  var3 += replaceSubElementRecurse(var6, var1, var2);
               }
            }
         }

         return var3;
      }
   }

   public static ICDecl getDefinition(ICElement var0) {
      if (var0 instanceof ICDecl var4) {
         return var4;
      } else {
         return var0 instanceof ICAssignment var1 && var1.getLeft() instanceof ICDecl var2 ? var2 : null;
      }
   }

   public static ICElement getDefinitionInitialValue(ICElement var0) {
      return var0 instanceof ICAssignment var1 && var1.getLeft() instanceof ICDecl ? var1.getRight() : null;
   }

   public static boolean isDeclareAndAssign(ICElement var0) {
      return var0 instanceof ICAssignment var1 && var1.getLeft() instanceof ICDecl;
   }

   public static boolean visitICStatementDepthPost(ICVisitor var0, ICBlock var1, int var2, CVisitResults var3) {
      if (var3 == null) {
         var3 = new CVisitResults();
      }

      for (int var4 = var2; var4 < var1.size(); var4++) {
         visitICStatementDepthPostInternal(var0, var1.get(var4), null, var3);
         if (var3.isInterruptedVisit()) {
            break;
         }
      }

      return var3.isVisitedSuccessfully();
   }

   public static boolean visitICStatementDepthPost(ICVisitor var0, ICStatement var1, CVisitResults var2) {
      if (var2 == null) {
         var2 = new CVisitResults();
      }

      visitICStatementDepthPostInternal(var0, var1, null, var2);
      return var2.isVisitedSuccessfully();
   }

   private static void visitICStatementDepthPostInternal(ICVisitor var0, ICStatement var1, ICStatement var2, CVisitResults var3) {
      List var4 = var1.getSubElements();
      var3.pushParent(var1);

      for (ICElement var6 : var4) {
         if (!isClassMethodField(var6) && var6 instanceof ICStatement var7) {
            visitICStatementDepthPostInternal(var0, var7, var1, var3);
            if (var3.isInterruptedVisit()) {
               return;
            }
         }
      }

      var3.popParent();
      var3.setReplacedNode(var1);
      var0.process(var1, var2, var3);
   }

   public static boolean containsBreak(ICStatement var0) {
      return containsStatement(var0, ICBreak.class);
   }

   public static boolean containsLabel(ICStatement var0) {
      return containsStatement(var0, ICLabel.class);
   }

   public static boolean containsStatement(ICStatement var0, Class var1) {
      if (var1.isInstance(var0)) {
         return true;
      } else {
         if (var0 instanceof ICCompound var2) {
            for (ICBlock var4 : var2.getBlocks()) {
               for (ICStatement var6 : var4) {
                  if (containsStatement(var6, var1)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public static String format(ICElement var0) {
      COutputSink var1 = new COutputSink(0L);
      var0.generate(var1);
      return var1.format();
   }

   public static class BreakFlowResult {
      private static CUtil.BreakFlowResult TRUE = new CUtil.BreakFlowResult(CUtil.BreakFlowStatus.TRUE, true);
      private static CUtil.BreakFlowResult FALSE = new CUtil.BreakFlowResult(CUtil.BreakFlowStatus.FALSE, false);
      private static CUtil.BreakFlowResult BREAK = new CUtil.BreakFlowResult(CUtil.BreakFlowStatus.BREAK, true);
      private static CUtil.BreakFlowResult UNKNOWN = new CUtil.BreakFlowResult(CUtil.BreakFlowStatus.UNKNOWN, false);
      private CUtil.BreakFlowStatus status;
      private boolean lastInstruction;

      public BreakFlowResult(CUtil.BreakFlowStatus var1, boolean var2) {
         this.status = var1;
         this.lastInstruction = var2;
      }

      public CUtil.BreakFlowStatus getStatus() {
         return this.status;
      }

      public boolean isLastInstruction() {
         return this.lastInstruction;
      }
   }

   public static enum BreakFlowStatus {
      TRUE,
      FALSE,
      BREAK,
      BOTH,
      UNKNOWN;
   }
}
