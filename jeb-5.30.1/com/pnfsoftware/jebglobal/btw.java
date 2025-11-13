package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.android.ir.IDExpression;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDGlobalContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDOperation;
import com.pnfsoftware.jeb.core.units.code.java.JavaOperatorType;

public class btw {
   private IDGlobalContext q;

   public btw(IDGlobalContext var1) {
      this.q = var1;
   }

   private IDOperation q(JavaOperatorType var1, IDExpression var2, IDExpression var3) {
      return this.q.createOperation(null, var1, var2, var3);
   }

   public IDOperation q(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.ADD, var1, var2);
   }

   public IDOperation RF(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.SUB, var1, var2);
   }

   public IDOperation xK(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.MUL, var1, var2);
   }

   public IDOperation Dw(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.DIV, var1, var2);
   }

   public IDOperation Uv(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.REM, var1, var2);
   }

   public IDOperation oW(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.AND, var1, var2);
   }

   public IDOperation gO(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.OR, var1, var2);
   }

   public IDOperation nf(IDExpression var1, IDExpression var2) {
      return this.q(JavaOperatorType.XOR, var1, var2);
   }

   public IDOperation q(IDExpression var1) {
      return this.q(JavaOperatorType.NOT, null, var1);
   }

   public IDOperation gP(IDExpression var1, IDExpression var2) {
      return this.q.createPredicate(JavaOperatorType.LOG_AND, var1, var2);
   }

   public IDOperation za(IDExpression var1, IDExpression var2) {
      return this.q.createPredicate(JavaOperatorType.LOG_OR, var1, var2);
   }

   public IDOperation RF(IDExpression var1) {
      return this.q.createPredicate(JavaOperatorType.LOG_NOT, null, var1);
   }
}
