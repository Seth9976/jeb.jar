package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.COperatorType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBlock;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICBreak;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICCall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICConstantInteger32;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICContinue;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICDoWhileStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICElementFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICExpression;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICForStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICGoto;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIdentifier;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICIfStm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICJumpFar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabel;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICLabelFactory;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICMethod;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICPredicate;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICReturn;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICThrow;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ast.ICWhileStm;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;

public class aet {
   private ICMethod q;
   private ICElementFactory RF;
   private ICConstantFactory xK;
   private ICLabelFactory Dw;

   public aet(ICMethod var1) {
      this.q = var1;
      this.RF = var1.getElementFactory();
      this.xK = var1.getConstantFactory();
      this.Dw = var1.getLabelFactory();
   }

   public void q(String... var1) {
      ICPredicate var2 = this.RF.createPredicate(this.xK.createInt32(1));
      ArrayDeque var3 = new ArrayDeque();
      ArrayDeque var4 = new ArrayDeque();
      ICBlock var5 = this.q.getBody();
      int var6 = 0;

      for (String var10 : var1) {
         var10 = var10.trim();
         if (!var10.isEmpty() && !var10.equals("pass") && !var10.equals(";")) {
            if (var10.endsWith(":")) {
               String var11 = var10.substring(0, var10.length() - 1);
               Assert.a(var11.length() > 0 && !var11.contains(":"));
               this.Dw.create(var6, var11);
            }

            var6++;
         }
      }

      var6 = 0;

      for (String var22 : var1) {
         var22 = var22.trim();
         if (!var22.isEmpty() && !var22.equals("pass") && !var22.equals(";")) {
            if (var22.equals("{") || var22.equals("block")) {
               ICBlock var44 = this.RF.createBlock();
               var5.add(var44);
               var3.push(var44);
               var4.push(var5);
               var5 = var44;
            } else if (var22.equals("}") || var22.equals("block-end:")) {
               ICStatement var43 = (ICStatement)var3.pop();
               Assert.a(var43 instanceof ICBlock);
               var5 = (ICBlock)var4.pop();
            } else if (var22.equals("do")) {
               ICBlock var42 = this.RF.createBlock();
               ICDoWhileStm var59 = this.RF.createDoWhileStm(var42, var2);
               var5.add(var59);
               var3.push(var59);
               var4.push(var5);
               var5 = var42;
            } else if (var22.startsWith("do-end ")) {
               ICPredicate var41 = this.RF(var22.substring(6));
               ICStatement var58 = (ICStatement)var3.pop();
               Assert.a(var58 instanceof ICDoWhileStm);
               ((ICDoWhileStm)var58).setPredicate(var41);
               var5 = (ICBlock)var4.pop();
            } else if (var22.startsWith("while ")) {
               ICPredicate var40 = this.RF(var22.substring(5));
               ICBlock var57 = this.RF.createBlock();
               ICWhileStm var64 = this.RF.createWhileStm(var40, var57);
               var5.add(var64);
               var3.push(var64);
               var4.push(var5);
               var5 = var57;
            } else if (var22.equals("while-end")) {
               ICStatement var39 = (ICStatement)var3.pop();
               Assert.a(var39 instanceof ICWhileStm);
               var5 = (ICBlock)var4.pop();
            } else if (var22.startsWith("for ")) {
               String[] var38 = var22.substring(3).split(";");
               ICStatement var56 = this.Uv(var38[0]);
               ICPredicate var63 = this.RF(var38[1]);
               ICStatement var65 = this.Uv(var38[2]);
               ICBlock var15 = this.RF.createBlock();
               ICForStm var16 = this.RF.createForLoop(var56, var63, var65, var15);
               var5.add(var16);
               var3.push(var16);
               var4.push(var5);
               var5 = var15;
            } else if (var22.equals("for-end")) {
               ICStatement var37 = (ICStatement)var3.pop();
               Assert.a(var37 instanceof ICForStm);
               var5 = (ICBlock)var4.pop();
            } else if (var22.startsWith("if ")) {
               ICPredicate var36 = this.RF(var22.substring(2));
               ICBlock var55 = this.RF.createBlock();
               ICIfStm var62 = this.RF.createIfStm(var36, var55);
               var5.add(var62);
               var3.push(var62);
               var4.push(var5);
               var5 = var55;
            } else if (var22.startsWith("elif ")) {
               ICPredicate var35 = this.RF(var22.substring(4));
               ICBlock var54 = this.RF.createBlock();
               ICStatement var61 = (ICStatement)var3.peek();
               Assert.a(var61 instanceof ICIfStm && !((ICIfStm)var61).hasDefaultBlock());
               ICIfStm var14 = (ICIfStm)var61;
               var14.addBranch(var35, var54);
               var5 = var54;
            } else if (var22.equals("else")) {
               ICBlock var34 = this.RF.createBlock();
               ICStatement var53 = (ICStatement)var3.peek();
               Assert.a(var53 instanceof ICIfStm);
               ICIfStm var60 = (ICIfStm)var53;
               var60.setDefaultBlock(var34);
               var5 = var34;
            } else if (var22.equals("if-end")) {
               ICStatement var33 = (ICStatement)var3.pop();
               Assert.a(var33 instanceof ICIfStm);
               var5 = (ICBlock)var4.pop();
            } else if (var22.endsWith(":")) {
               String var32 = var22.substring(0, var22.length() - 1);
               Assert.a(var32.length() > 0 && !var32.contains(":"));
               ICLabel var52 = this.Dw.get(var32);
               Assert.a(var52 != null && var52.getOffset() == var6);
               var5.add(var52);
            } else if (var22.startsWith("goto ")) {
               String var24 = var22.substring(4).trim();
               ICLabel var12 = this.Dw.get(var24);
               Assert.a(var12 != null);
               ICGoto var13 = this.RF.createGoto(var12);
               var5.add(var13);
            } else if (var22.startsWith("break")) {
               ICLabel var25 = null;
               if (var22.length() > 5 && var22.charAt(5) == ' ') {
                  String var45 = var22.substring(5).trim();
                  var25 = this.Dw.get(var45);
                  Assert.a(var25 != null);
               }

               ICBreak var46 = this.RF.createBreak(var25);
               var5.add(var46);
            } else if (var22.startsWith("continue")) {
               ICLabel var26 = null;
               if (var22.length() > 8 && var22.charAt(8) == ' ') {
                  String var47 = var22.substring(8).trim();
                  var26 = this.Dw.get(var47);
                  Assert.a(var26 != null);
               }

               ICContinue var48 = this.RF.createContinue(var26);
               var5.add(var48);
            } else if (var22.startsWith("return")) {
               ICExpression var27 = null;
               if (var22.length() > 6 && var22.charAt(6) == ' ') {
                  var27 = this.q(var22.substring(6));
               }

               ICReturn var49 = this.RF.createReturn(var27);
               var5.add(var49);
            } else if (var22.startsWith("throw")) {
               ICExpression var28 = null;
               if (var22.length() > 5 && var22.charAt(5) == ' ') {
                  var28 = this.q(var22.substring(5));
               }

               ICThrow var50 = this.RF.createThrow(var28);
               var5.add(var50);
            } else if (var22.startsWith("jump")) {
               ICExpression var29 = null;
               if (var22.length() > 4 && var22.charAt(4) == ' ') {
                  var29 = this.q(var22.substring(4));
               }

               ICJumpFar var51 = this.RF.createJumpFar(var29);
               var5.add(var51);
            } else if (var22.startsWith("foo(")) {
               ICCall var30 = this.oW(var22);
               var5.add(var30);
            } else {
               ICStatement var31 = this.Uv(var22);
               var5.add(var31);
            }

            var6++;
         }
      }

      Assert.a(var3.isEmpty());
      Assert.a(var4.isEmpty());
   }

   private ICExpression q(String var1) {
      var1 = var1.trim();
      if (var1.isEmpty()) {
         throw new RuntimeException("Cannot parse empty expression");
      } else if (var1.equalsIgnoreCase("true")) {
         return this.xK.createInt32(1);
      } else if (var1.equalsIgnoreCase("false")) {
         return this.xK.createInt32(0);
      } else if (var1.startsWith("foo(")) {
         return this.oW(var1);
      } else {
         for (COperatorType var4 : Arrays.asList(
            COperatorType.ADD, COperatorType.SUB, COperatorType.EQ, COperatorType.NE, COperatorType.LE, COperatorType.LT, COperatorType.GE, COperatorType.GT
         )) {
            String var5 = var4.toString();
            int var6 = var1.indexOf(var5);
            if (var6 >= 0) {
               String var7 = var1.substring(0, var6);
               String var8 = var1.substring(var6 + var5.length());
               return this.RF.createOperation(var4, this.q(var7), this.q(var8));
            }
         }

         char var10 = var1.charAt(0);
         return (ICExpression)((var10 < '0' || var10 > '9') && !var1.startsWith("0x") ? this.xK(var1) : this.Dw(var1));
      }
   }

   private ICPredicate RF(String var1) {
      var1 = var1.trim();
      ICExpression var2 = this.q(var1);
      return this.RF.createPredicate(var2);
   }

   private ICIdentifier xK(String var1) {
      var1 = var1.trim();

      for (ICIdentifier var3 : this.q.getIdentifierManager().getIdentifiers()) {
         if (var1.equals(var3.getName())) {
            return var3;
         }
      }

      throw new RuntimeException("Cannot parse as var: " + var1);
   }

   private ICConstantInteger32 Dw(String var1) {
      var1 = var1.trim();
      int var2 = Conversion.stringToInt(var1, -1);
      if (var2 < 0) {
         throw new RuntimeException("Cannot parse as an unsigned int32: " + var1);
      } else {
         return this.xK.createInt32(var2);
      }
   }

   private ICStatement Uv(String var1) {
      var1 = var1.trim();
      int var2 = var1.indexOf(61);
      if (var2 >= 0) {
         ICIdentifier var3 = this.xK(var1.substring(0, var2));
         ICExpression var4 = this.q(var1.substring(var2 + 1));
         return this.RF.createAssignment(var3, var4);
      } else {
         throw new RuntimeException("Cannot parse as a statement: " + var1);
      }
   }

   private ICCall oW(String var1) {
      var1 = var1.trim();
      Assert.a(var1.startsWith("foo(") && var1.endsWith(")"));
      ArrayList var2 = new ArrayList();
      String var3 = var1.substring(4, var1.length() - 1).trim();
      if (!var3.isEmpty()) {
         for (String var7 : var3.split(",")) {
            var2.add(this.q(var7));
         }
      }

      ICMethod var9 = this.q.getMethodFactory().create("foo");
      return this.RF.createCall(var9, var2);
   }
}
