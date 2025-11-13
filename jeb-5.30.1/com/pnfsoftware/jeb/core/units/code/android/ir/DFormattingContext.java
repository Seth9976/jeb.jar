package com.pnfsoftware.jeb.core.units.code.android.ir;

import com.pnfsoftware.jeb.core.units.code.java.IJavaType;
import com.pnfsoftware.jeb.util.format.Strings;

public class DFormattingContext {
   private StringBuilder sb = new StringBuilder();
   private IDMethodContext ctx = null;
   private boolean usePreferredNames = false;
   private boolean displayTypes = true;
   private boolean displayCanThrow = true;

   public DFormattingContext() {
   }

   public DFormattingContext(IDMethodContext var1) {
      this.setMethodContext(var1);
   }

   public void setMethodContext(IDMethodContext var1) {
      this.ctx = var1;
   }

   public IDMethodContext getMethodContext() {
      return this.ctx;
   }

   public void setUsePreferredNames(boolean var1) {
      this.usePreferredNames = var1;
   }

   public boolean isUsePreferredNames() {
      return this.usePreferredNames;
   }

   public void setDisplayTypes(boolean var1) {
      this.displayTypes = var1;
   }

   public boolean isDisplayTypes() {
      return this.displayTypes;
   }

   public void setDisplayCanThrow(boolean var1) {
      this.displayCanThrow = var1;
   }

   public boolean isDisplayCanThrow() {
      return this.displayCanThrow;
   }

   public DFormattingContext append(Object var1) {
      this.sb.append(var1 == null ? "null" : var1.toString());
      return this;
   }

   public DFormattingContext append(String var1) {
      this.sb.append(var1);
      return this;
   }

   public DFormattingContext append(int var1) {
      this.sb.append(var1);
      return this;
   }

   public DFormattingContext append(char var1) {
      this.sb.append(var1);
      return this;
   }

   public DFormattingContext appendFormat(String var1, Object... var2) {
      Strings.ff(this.sb, var1, var2);
      return this;
   }

   public void space() {
      this.sb.append(' ');
   }

   public void paren() {
      this.sb.append('(');
   }

   public void parenClose() {
      this.sb.append(')');
   }

   public void brace() {
      this.sb.append('{');
   }

   public void braceClose() {
      this.sb.append('}');
   }

   public void bracket() {
      this.sb.append('[');
   }

   public void bracketClose() {
      this.sb.append(']');
   }

   public void angle() {
      this.sb.append('<');
   }

   public void angleClose() {
      this.sb.append('>');
   }

   public void appendFormattedTypeIf(IJavaType var1) {
      if (this.displayTypes) {
         this.appendFormattedType(var1);
      }
   }

   public void appendFormattedType(IJavaType var1) {
      this.angle();
      if (var1 == null) {
         this.append("unknown_type");
      } else {
         var1.format(this);
      }

      this.angleClose();
   }

   @Override
   public String toString() {
      return this.sb.toString();
   }
}
