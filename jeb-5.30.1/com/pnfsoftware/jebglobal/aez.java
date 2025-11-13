package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.code.coordinates.MethodCoordinates;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CIdentifierClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.CUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICAssignment;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICClass;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDecl;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICField;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifierManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLeftExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICNamingEngine;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICOperation;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Ser
public class aez implements ICNamingEngine {
   private static final ILogger zz = GlobalLog.getLogger(aez.class);
   static final String q = "param";
   static final String RF = "counter";
   static final String xK = "cond";
   static final String Dw = "result";
   static final Integer Uv = 0;
   static final Integer oW = 1;
   static final Integer gO = 1;
   static final Integer nf = 1;
   static final Integer gP = 1;
   static final Integer za = 2;
   static final Integer lm = 2;

   @Override
   public boolean beautifyIdentifierNames(ICClass var1, IDynamicContentManager var2) {
      return false;
   }

   @Override
   public boolean beautifyIdentifierNames(ICField var1, IDynamicContentManager var2) {
      return false;
   }

   @Override
   public boolean beautifyIdentifierNames(ICMethod var1, IDynamicContentManager var2) {
      return new aez.eo(var1, var2).q();
   }

   private static class eo {
      ICMethod q;
      ICIdentifierManager RF;
      IDynamicContentManager xK;
      Map Dw = new IdentityHashMap();

      eo(ICMethod var1, IDynamicContentManager var2) {
         this.q = var1;
         this.RF = var1.getIdentifierManager();
         this.xK = var2;
      }

      public boolean q() {
         boolean var1 = false;
         if (this.RF()) {
            var1 = true;
         }

         if (this.Dw()) {
            var1 = true;
         }

         return var1;
      }

      private boolean RF() {
         boolean var1 = false;
         if (this.xK()) {
            var1 = true;
         }

         if (this.Uv()) {
            var1 = true;
         }

         return var1;
      }

      private boolean xK() {
         boolean var1 = false;
         List var2 = this.q.getParameters();
         if (var2 != null) {
            for (ICDecl var4 : var2) {
               ICIdentifier var5 = var4.getIdentifier();
               String var6 = this.q(var5, "param", 0);
               if (var6 != null && this.q(var5, var6, aez.oW)) {
                  var1 = true;
               }
            }
         }

         return var1;
      }

      private boolean Dw() {
         boolean var1 = false;
         this.q(this.q.getBody(), null);

         for (ICIdentifier var3 : this.RF.getIdentifiers()) {
            if (this.q(var3)) {
               var1 = true;
            }
         }

         return var1;
      }

      private boolean q(ICElement var1, ICElement var2) {
         Assert.a(var1 != null);
         boolean var3 = false;
         if (this.q(var1)) {
            var3 = true;
         }

         for (ICElement var5 : var1.getSubElements()) {
            if (!CUtil.isClassMethodField(var5) && this.q(var5, var1)) {
               var3 = true;
            }
         }

         return var3;
      }

      private boolean q(ICElement var1) {
         ICDecl var2 = CUtil.getDefinition(var1);
         return var2 != null ? this.q(var2.getIdentifier()) : false;
      }

      private boolean q(ICIdentifier var1) {
         boolean var2 = false;
         if (!this.Dw.keySet().contains(var1)) {
            String var3 = this.RF(var1);
            if (var3 != null && this.q(var1, var3, aez.Uv)) {
               var2 = true;
            }
         }

         return var2;
      }

      private String RF(ICIdentifier var1) {
         String var2 = var1.getType().getSignature();
         String var3;
         if (var2.endsWith("*")) {
            var3 = "ptr";
         } else if (var2.contains("[")) {
            var3 = "arr";
         } else {
            var3 = "v";
         }

         return this.q(var1, var3, 0);
      }

      private boolean xK(ICIdentifier var1) {
         if ((var1.getFlags() & 2) != 0) {
            return false;
         } else {
            return var1.getIdentifierClass() != null ? var1.getIdentifierClass() != CIdentifierClass.GLOBAL : true;
         }
      }

      private boolean q(ICIdentifier var1, String var2, int var3) {
         if (!this.xK(var1)) {
            return false;
         } else {
            Integer var4 = (Integer)this.Dw.get(var1);
            if (var4 == null) {
               var1.setName(var2, this.xK);
               this.Dw.put(var1, var3);
               return true;
            } else if (var3 <= var4 && (var3 != var4 || var2.length() <= var1.getName().length())) {
               return false;
            } else {
               var1.setName(var2, this.xK);
               this.Dw.put(var1, var3);
               return true;
            }
         }
      }

      private boolean Uv() {
         return this.RF(this.q.getBody(), null);
      }

      private boolean RF(ICElement var1, ICElement var2) {
         Assert.a(var1 != null);
         boolean var3 = false;
         if (this.xK(var1, var2)) {
            var3 = true;
         }

         for (ICElement var5 : var1.getSubElements()) {
            if (!(var5 instanceof ICMethod) && this.RF(var5, var1)) {
               var3 = true;
            }
         }

         return var3;
      }

      private boolean xK(ICElement var1, ICElement var2) {
         boolean var3 = false;
         if (var1 instanceof ICForStm) {
            var3 = this.q((ICForStm)var1);
         }

         if (var1 instanceof ICPredicate) {
            var3 = this.q((ICPredicate)var1, var2);
         }

         if (var1 instanceof ICReturn) {
            var3 = this.q((ICReturn)var1);
         }

         if (var1 instanceof ICCall) {
            var3 = this.q((ICCall)var1);
         }

         if (var1 instanceof ICAssignment && ((ICAssignment)var1).getRight() instanceof ICCall) {
            ICLeftExpression var4 = ((ICAssignment)var1).getLeft();
            ICIdentifier var5 = null;
            if (var4 instanceof ICIdentifier) {
               var5 = (ICIdentifier)var4;
            } else if (var4 instanceof ICDecl) {
               var5 = ((ICDecl)var4).getIdentifier();
            }

            if (var5 != null) {
               ICCall var6 = (ICCall)((ICAssignment)var1).getRight();
               var3 = this.q(var6, var5);
            }
         }

         return var3;
      }

      private boolean q(ICCall var1, ICIdentifier var2) {
         ICMethod var3 = var1.getMethod();
         if (var3 == null || !var1.isStatic()) {
            return false;
         } else if (this.xK == null) {
            return false;
         } else {
            String var4 = this.xK == null ? null : this.xK.getMethodName(new MethodCoordinates(var3.getIndex()));
            if (var4 == null) {
               var4 = var3.getName();
            }

            com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.CU var5 = com.pnfsoftware.jeb.corei.parsers.asm.type.axgen.EE.q().q(var4);
            if (var5 != null) {
               String var6 = var5.RF();
               if (var6 != null) {
                  String var7 = this.q(var2, var6, 1, var6);
                  if (var7 != null && this.q(var2, var7, aez.za)) {
                     return true;
                  }
               }
            }

            return false;
         }
      }

      private boolean q(ICCall var1) {
         ICMethod var2 = var1.getMethod();
         if (var2 != null && var1.getArgumentCount() != 0) {
            boolean var3 = false;
            int var4 = 0;

            for (ICDecl var6 : var2.getParameters()) {
               if (this.q(var6.getName())) {
                  if (var4 >= var1.getArguments().size()) {
                     break;
                  }

                  ICExpression var7 = (ICExpression)var1.getArguments().get(var4);
                  ICIdentifier var8 = null;
                  if (var7 instanceof ICOperation var9) {
                     if (var9.getOperatorType() == COperatorType.REF) {
                        ICExpression var10 = var9.getFirstOperand();
                        if (var10 instanceof ICIdentifier) {
                           var8 = (ICIdentifier)var10;
                        }
                     } else if (var9.getOperatorType() == COperatorType.CAST) {
                        ICExpression var12 = var9.getFirstOperand();
                        if (var12 instanceof ICIdentifier) {
                           var8 = (ICIdentifier)var12;
                        }
                     }
                  }

                  if (var7 instanceof ICIdentifier) {
                     var8 = (ICIdentifier)var7;
                  }

                  if (var8 != null) {
                     String var11 = this.q(var8, var6.getName(), 1, var6.getName());
                     if (var11 != null && this.q(var8, var11, aez.za)) {
                        var3 = true;
                     }
                  }
               }

               var4++;
            }

            return var3;
         } else {
            return false;
         }
      }

      private boolean q(String var1) {
         return !var1.startsWith("par");
      }

      private boolean q(ICPredicate var1, ICElement var2) {
         ICIdentifier var3 = this.q(var1);
         if (var3 == null) {
            return false;
         } else {
            String var4 = this.q(var3, var2);
            if (var4 == null) {
               return false;
            } else {
               String var5 = this.q(var3, var4, 1, var4);
               return var5 == null ? false : this.q(var3, var5, aez.nf);
            }
         }
      }

      private String q(ICIdentifier var1, ICElement var2) {
         String var3 = null;
         if (var2 instanceof ICDoWhileStm && ((ICDoWhileStm)var2).getBody().size() >= 1) {
            ICBlock var4 = ((ICDoWhileStm)var2).getBody();
            ICStatement var5 = var4.getLast();
            if (var5 instanceof ICAssignment && ((ICAssignment)var5).isUnaryOperatorAssignment() && ((ICAssignment)var5).getLeft() == var1) {
               var3 = "counter";
            }
         }

         return var3;
      }

      private ICIdentifier q(ICPredicate var1) {
         ICIdentifier var2 = null;
         if (var1.getExpression() instanceof ICIdentifier) {
            var2 = (ICIdentifier)var1.getExpression();
         } else if (CUtil.isOperation(var1.getExpression(), COperatorType.LOG_IDENT)
            && ((ICOperation)var1.getExpression()).getFirstOperand() instanceof ICIdentifier) {
            var2 = (ICIdentifier)((ICOperation)var1.getExpression()).getFirstOperand();
         }

         return var2;
      }

      private boolean q(ICReturn var1) {
         if (var1.getExpression() instanceof ICIdentifier var3) {
            String var4 = this.q(var3, "result", 1, "result");
            if (var4 != null) {
               return this.q(var3, var4, aez.gO);
            }
         }

         return false;
      }

      private boolean q(ICForStm var1) {
         ICIdentifier var2 = this.RF(var1);
         if (var2 == null) {
            return false;
         } else {
            this.RF(var1, var2);
            return true;
         }
      }

      private ICIdentifier RF(ICForStm var1) {
         ICIdentifier var2 = this.xK(var1);
         if (var2 == null) {
            return null;
         } else if (!this.q(var1, var2)) {
            return null;
         } else {
            return this.Dw(var2) ? var2 : null;
         }
      }

      private ICIdentifier xK(ICForStm var1) {
         ICIdentifier var2 = null;
         ICStatement var3 = var1.getPreStatement();
         if (var3 instanceof ICAssignment) {
            if (((ICAssignment)var3).getLeft() instanceof ICDecl) {
               var2 = ((ICDecl)((ICAssignment)var3).getLeft()).getIdentifier();
            } else if (((ICAssignment)var3).getLeft() instanceof ICIdentifier) {
               var2 = (ICIdentifier)((ICAssignment)var3).getLeft();
            }
         }

         return var2;
      }

      private boolean q(ICForStm var1, ICIdentifier var2) {
         ICStatement var3 = var1.getPostStatement();
         return var3 instanceof ICAssignment && ((ICAssignment)var3).getLeft().equals(var2);
      }

      private boolean Dw(ICIdentifier var1) {
         String var2 = this.q(var1, "counter", 1, "i", "j", "k", "counter");
         return var2 != null ? this.q(var1, var2, aez.lm) : false;
      }

      private boolean RF(ICForStm var1, ICIdentifier var2) {
         Assert.a(var1 != null && var2 != null);
         ICPredicate var3 = var1.getPredicate();
         if (var3.getExpression() instanceof ICOperation && ((ICOperation)var3.getExpression()).getCountOfOperands() == 2) {
            ICIdentifier var4 = null;
            if (((ICOperation)var3.getExpression()).getFirstOperand() == var2 && ((ICOperation)var3.getExpression()).getSecondOperand() instanceof ICIdentifier
               )
             {
               var4 = (ICIdentifier)((ICOperation)var3.getExpression()).getSecondOperand();
            }

            if (var4 != null) {
               String var5 = null;
               COperatorType var6 = ((ICOperation)var3.getExpression()).getOperatorType();
               if (var6 == COperatorType.GE || var6 == COperatorType.GT) {
                  var5 = this.q(var4, "min", 1, "min");
               } else if (var6 == COperatorType.LE || var6 == COperatorType.LT) {
                  var5 = this.q(var4, "max", 1, "max");
               }

               if (var5 != null) {
                  return this.q(var4, var5, aez.gP);
               }
            }
         }

         return false;
      }

      private String q(ICIdentifier var1, String var2, int var3, String... var4) {
         String var5 = var1.getName();

         for (String var9 : var4) {
            if (var9.equals(var5)) {
               return var5;
            }
         }

         Set var11 = this.oW();

         for (String var10 : var4) {
            if (!var11.contains(var10)) {
               return var10;
            }
         }

         Assert.a(!var2.isEmpty());
         String var13 = var2 + (Character.isDigit(var2.charAt(var2.length() - 1)) ? "_" : "");
         int var15 = var3;

         while (true) {
            String var17 = var13 + var15;
            if (var17.equals(var5)) {
               return var5;
            }

            if (!var11.contains(var17)) {
               return var17;
            }

            var15++;
         }
      }

      private Set oW() {
         HashSet var1 = new HashSet();

         for (ICIdentifier var3 : this.RF.getIdentifiers()) {
            var1.add(var3.getName());
         }

         return var1;
      }
   }
}
