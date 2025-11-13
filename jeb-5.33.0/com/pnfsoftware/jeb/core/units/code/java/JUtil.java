package com.pnfsoftware.jeb.core.units.code.java;

import com.pnfsoftware.jeb.core.output.text.TextPartUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class JUtil {
   private static final ILogger logger = GlobalLog.getLogger(JUtil.class);

   public static String generate(IJavaElement var0) {
      JavaOutputSink var1 = new JavaOutputSink(0);
      var0.generate(var1);
      return TextPartUtil.buildRawTextFromPart(var1);
   }

   public static IJavaElement findParent(IJavaElement var0, IJavaElement var1) {
      List var2 = var0.getSubElements();

      for (IJavaElement var4 : var2) {
         if (var4 == var1) {
            return var0;
         }
      }

      for (IJavaElement var7 : var2) {
         IJavaElement var5 = findParent(var7, var1);
         if (var5 != null) {
            return var5;
         }
      }

      return null;
   }

   public static boolean isClassMethodField(IJavaElement var0) {
      return var0 instanceof IJavaMethod || var0 instanceof IJavaClass || var0 instanceof IJavaField;
   }

   public static boolean isIntegerConstant(IJavaElement var0, int var1) {
      return var0 instanceof IJavaConstant && ((IJavaConstant)var0).getType().isInt() ? ((IJavaConstant)var0).getInt() == var1 : false;
   }

   public static boolean isStringConstant(IJavaElement var0) {
      return var0 instanceof IJavaConstant && ((IJavaConstant)var0).isString();
   }

   public static String getStringConstant(IJavaElement var0) {
      return var0 instanceof IJavaConstant && ((IJavaConstant)var0).isString() ? ((IJavaConstant)var0).getString() : null;
   }

   public static IJavaLabel checkIfGoto(IJavaStatement var0) {
      if (!(var0 instanceof IJavaIf var1)) {
         return null;
      } else if (var1.size() != 1) {
         return null;
      } else {
         IJavaBlock var2 = var1.getBranchBody(0);
         return var2.size() == 1 && var2.get(0) instanceof IJavaGoto ? ((IJavaGoto)var2.get(0)).getLabel() : null;
      }
   }

   public static boolean isGotoTo(IJavaStatement var0, IJavaLabel var1) {
      return var0 instanceof IJavaGoto && ((IJavaGoto)var0).getLabel() == var1;
   }

   public static boolean isIf(IJavaStatement var0) {
      return var0 instanceof IJavaIf var1 && var1.size() == 1;
   }

   public static boolean isIfElse(IJavaStatement var0) {
      return var0 instanceof IJavaIf var1 && var1.size() == 2 && var1.hasDefaultBlock();
   }

   public static boolean isIfNoElse(IJavaStatement var0) {
      return var0 instanceof IJavaIf var1 ? !var1.hasDefaultBlock() : false;
   }

   public static IJavaLabel isIfGoto(IJavaStatement var0) {
      if (var0 instanceof IJavaIf var1 && var1.size() == 1) {
         IJavaBlock var2 = var1.getBranchBody(0);
         if (var2.size() == 1 && var2.get(0) instanceof IJavaGoto) {
            return ((IJavaGoto)var2.get(0)).getLabel();
         }
      }

      return null;
   }

   public static IJavaStatement getFirstRealStatement(IJavaBlock var0, int var1) {
      if (var1 >= var0.size()) {
         return null;
      } else {
         IJavaStatement var2 = var0.get(var1);
         if (!(var2 instanceof IJavaCompound)) {
            return var2;
         } else if (var2 instanceof IJavaDoWhile) {
            IJavaStatement var6 = getFirstRealStatement(((IJavaDoWhile)var2).getBody(), 0);
            return var6 == null ? var2 : var6;
         } else if (var2 instanceof IJavaWhile) {
            if (!((IJavaWhile)var2).getPredicate().isLitteralTrue()) {
               return var2;
            } else {
               IJavaStatement var5 = getFirstRealStatement(((IJavaWhile)var2).getBody(), 0);
               return var5 == null ? var2 : var5;
            }
         } else if (var2 instanceof IJavaIf) {
            if (!((IJavaIf)var2).getBranchPredicate(0).isLitteralTrue()) {
               return var2;
            } else {
               IJavaStatement var4 = getFirstRealStatement(((IJavaIf)var2).getBranchBody(0), 0);
               return var4 == null ? var2 : var4;
            }
         } else if (var2 instanceof IJavaTry) {
            IJavaStatement var3 = getFirstRealStatement(((IJavaTry)var2).getTryBody(), 0);
            return var3 == null ? var2 : var3;
         } else {
            return var2;
         }
      }
   }

   public static IJavaCatchBlock isTryCatchall(IJavaBlock var0, int var1) {
      if (var1 < var0.size() && var0.get(var1) instanceof IJavaTry) {
         IJavaTry var2 = (IJavaTry)var0.get(var1);
         return isTryCatchall(var2);
      } else {
         return null;
      }
   }

   public static IJavaCatchBlock isTryCatchall(IJavaTry var0) {
      return !var0.hasFinallyBlock() && var0.getCatchCount() == 1 && "Ljava/lang/Throwable;".equals(var0.getCatchType(0).getName())
         ? var0.getCatchBlock(0)
         : null;
   }

   public static IJavaIdentifier getVarLike(IJavaExpression var0) {
      if (var0 instanceof IJavaIdentifier) {
         return (IJavaIdentifier)var0;
      } else if (var0 instanceof IJavaOperation && ((IJavaOperation)var0).getOperator().isCast()) {
         IJavaExpression var1 = ((IJavaOperation)var0).getRight();
         return getVarLike(var1);
      } else {
         return null;
      }
   }

   public static boolean isImmOrVarLike(IJavaExpression var0) {
      if (var0 instanceof IJavaConstant || var0 instanceof IJavaIdentifier) {
         return true;
      } else if (var0 instanceof IJavaOperation && ((IJavaOperation)var0).getOperator().isCast()) {
         IJavaExpression var1 = ((IJavaOperation)var0).getRight();
         return isImmOrVarLike(var1);
      } else {
         return false;
      }
   }

   public static List getIdentifiers(IJavaElement var0) {
      ArrayList var1 = new ArrayList();
      var0.visitDepthPost(new JUtil$1(var1));
      return var1;
   }

   public static boolean isIdentOrDefinition(IJavaElement var0, IJavaIdentifier var1) {
      return var0 == var1 ? true : var0 instanceof IJavaDefinition && ((IJavaDefinition)var0).getIdentifier() == var1;
   }

   public static boolean isDeclarationOrDefinition(IJavaStatement var0) {
      return var0 instanceof IJavaDefinition ? true : var0 instanceof IJavaAssignment && ((IJavaAssignment)var0).getLeft() instanceof IJavaDefinition;
   }

   public static IJavaExpression isThrowLike(IJavaStatement var0, List var1) {
      if (var0 instanceof IJavaThrow) {
         return ((IJavaThrow)var0).getExpression();
      } else {
         if (var0 instanceof IJavaGoto) {
            IJavaLabel var2 = ((IJavaGoto)var0).getLabel();
            int var3 = 0;

            for (IJavaStatement var5 : var1) {
               if (var5 == var2) {
                  if (var3 + 1 < var1.size()) {
                     IJavaStatement var6 = (IJavaStatement)var1.get(var3 + 1);
                     if (var6 instanceof IJavaThrow) {
                        return ((IJavaThrow)var6).getExpression();
                     }
                  }
                  break;
               }

               var3++;
            }
         }

         return null;
      }
   }

   public static boolean isThrow(IJavaStatement var0, IJavaExpression var1) {
      return var0 instanceof IJavaThrow ? ((IJavaThrow)var0).getExpression() == var1 : false;
   }

   public static IJavaExpression getMonitorEnter(IJavaStatement var0) {
      return var0 instanceof IJavaMonitor && ((IJavaMonitor)var0).isEnter() ? ((IJavaMonitor)var0).getLock() : null;
   }

   public static IJavaExpression getMonitorExit(IJavaStatement var0) {
      return var0 instanceof IJavaMonitor && ((IJavaMonitor)var0).isExit() ? ((IJavaMonitor)var0).getLock() : null;
   }

   public static boolean isMonitorExit(IJavaElement var0) {
      return var0 instanceof IJavaMonitor && ((IJavaMonitor)var0).isExit();
   }

   public static boolean isMonitorExit(IJavaElement var0, IJavaExpression var1) {
      return var0 instanceof IJavaMonitor && ((IJavaMonitor)var0).isExit() && (var1 == null || ((IJavaMonitor)var0).getLock() == var1);
   }

   public static List getGotos(IJavaLabel var0, List var1) {
      ArrayList var2 = new ArrayList();

      for (IJavaStatement var4 : var1) {
         if (var4 instanceof IJavaGoto && ((IJavaGoto)var4).getLabel() == var0) {
            var2.add(var4);
         }
      }

      return var2;
   }

   public static IJavaLabel getGotoLabel(IJavaStatement var0) {
      return var0 instanceof IJavaGoto ? ((IJavaGoto)var0).getLabel() : null;
   }

   public static IJavaIdentifier getIdentifier(IJavaExpression var0) {
      if (var0 instanceof IJavaIdentifier) {
         return (IJavaIdentifier)var0;
      } else {
         return var0 instanceof IJavaDefinition ? ((IJavaDefinition)var0).getIdentifier() : null;
      }
   }

   public static int removeStatementsDeep(IJavaBlock var0, Predicate var1) {
      int var2 = 0;
      int var3 = 0;

      while (var3 < var0.size()) {
         IJavaStatement var4 = var0.get(var3);
         if (var1.test(var4)) {
            var0.remove(var3);
            var2++;
         } else {
            if (var4 instanceof IJavaCompound) {
               for (IJavaBlock var6 : ((IJavaCompound)var4).getBlocks()) {
                  var2 += removeStatementsDeep(var6, var1);
               }
            }

            var3++;
         }
      }

      return var2;
   }

   public static boolean isOrContainsLabel(IJavaStatement var0) {
      if (var0 instanceof IJavaLabel) {
         return true;
      } else {
         if (var0 instanceof IJavaCompound) {
            for (IJavaBlock var2 : ((IJavaCompound)var0).getBlocks()) {
               for (IJavaStatement var4 : var2) {
                  if (isOrContainsLabel(var4)) {
                     return true;
                  }
               }
            }
         }

         return false;
      }
   }

   public static boolean canThrow(IJavaStatement var0) {
      return !var0.visitDepthPost(new JUtil$2());
   }

   public static IJavaConstant getNegatedConstant(IJavaConstant var0, IJavaConstantFactory var1) {
      IJavaType var2 = var0.getType();
      if (var2 == null) {
         return null;
      } else {
         IJavaConstant var3 = null;
         if (var2.isBoolean()) {
            var3 = var1.createBoolean(!var0.getBoolean());
         } else if (var2.isByte()) {
            var3 = var1.createByte((byte)(-var0.getByte()));
         } else if (var2.isChar()) {
            var3 = var1.createChar((char)(-var0.getChar()));
         } else if (var2.isShort()) {
            var3 = var1.createShort((short)(-var0.getShort()));
         } else if (var2.isInt()) {
            var3 = var1.createInt(-var0.getInt());
         } else if (var2.isLong()) {
            var3 = var1.createLong(-var0.getLong());
         } else if (var2.isFloat()) {
            var3 = var1.createFloat(-var0.getFloat());
         } else if (var2.isDouble()) {
            var3 = var1.createDouble(-var0.getDouble());
         }

         return var3 == var0 ? null : var3;
      }
   }

   public static boolean isNonCompoundFlowBreaker(IJavaStatement var0) {
      return var0 instanceof IJavaReturn
         || var0 instanceof IJavaThrow
         || var0 instanceof IJavaGoto
         || var0 instanceof IJavaBreak
         || var0 instanceof IJavaContinue;
   }

   public static void moveStatements(IJavaBlock var0, int var1, int var2, IJavaBlock var3, int var4) {
      if (var0 == var3) {
         throw new IllegalArgumentException();
      } else {
         int var5 = var2 - var1;

         for (int var6 = var4; var5 > 0; var5--) {
            IJavaStatement var7 = var0.remove(var1);
            var3.insert(var6, var7);
            var6++;
         }
      }
   }

   public static boolean isSimpleBreak(IJavaStatement var0) {
      return var0 instanceof IJavaBreak && ((IJavaBreak)var0).getLabel() == null;
   }

   public static boolean isSimpleContinue(IJavaStatement var0) {
      return var0 instanceof IJavaContinue && ((IJavaContinue)var0).getLabel() == null;
   }

   public static boolean isFlowBreaker(IJavaStatement var0) {
      return var0 instanceof IJavaContinue
         || var0 instanceof IJavaBreak
         || var0 instanceof IJavaGoto
         || var0 instanceof IJavaReturn
         || var0 instanceof IJavaThrow;
   }

   public static boolean isIfContinue(IJavaStatement var0) {
      if (!isIf(var0)) {
         return false;
      } else {
         IJavaBlock var1 = ((IJavaIf)var0).getBranchBody(0);
         if (var1.size() != 1) {
            return false;
         } else {
            IJavaStatement var2 = var1.get(0);
            return isSimpleContinue(var2);
         }
      }
   }

   static IJavaExpression resolveLogicalNotOrSame(IJavaExpression var0, IJavaGlobalContext var1) {
      IJavaExpression var2 = resolveLogicalNot(var0, var1);
      return var2 == null ? var0 : var2;
   }

   public static IJavaExpression resolveLogicalNot(IJavaExpression var0, IJavaGlobalContext var1) {
      if (var0 instanceof IJavaOperation var2 && var2.getOperatorType() == JavaOperatorType.LOG_NOT && var2.getRight() instanceof IJavaOperation var3) {
         JavaOperatorType var8 = var3.getOperatorType();
         if (var8 == JavaOperatorType.LOG_NOT) {
            return var3.getRight();
         }

         if (var3.canReverse()) {
            Assert.a(var3.reverse(var1.getOperatorFactory()));
            return var3;
         }

         if (var8.isAnyOf(JavaOperatorType.LOG_AND, JavaOperatorType.LOG_OR)) {
            IJavaOperation var5 = var1.createOperation(null, JavaOperatorType.LOG_NOT, var3.getLeft());
            IJavaExpression var9 = resolveLogicalNotOrSame(var5, var1);
            IJavaOperation var6 = var1.createOperation(null, JavaOperatorType.LOG_NOT, var3.getRight());
            IJavaExpression var10 = resolveLogicalNotOrSame(var6, var1);
            JavaOperatorType var7 = var8 == JavaOperatorType.LOG_AND ? JavaOperatorType.LOG_OR : JavaOperatorType.LOG_AND;
            return var1.createOperation(var9, var7, var10);
         }
      }

      return null;
   }
}
