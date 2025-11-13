package com.pnfsoftware.jeb.util.encoding.jflex;

import com.pnfsoftware.jeb.util.format.Strings;

public class Token implements Comparable {
   public final TokenType type;
   public final long start;
   public final int length;
   public final byte pairValue;
   public final short kind = 0;

   public Token(TokenType var1, long var2, int var4) {
      this.type = var1;
      this.start = var2;
      this.length = var4;
      this.pairValue = 0;
   }

   public Token(TokenType var1, long var2, int var4, byte var5) {
      this.type = var1;
      this.start = var2;
      this.length = var4;
      this.pairValue = var5;
   }

   @Override
   public boolean equals(Object var1) {
      return !(var1 instanceof Token var2) ? false : this.start == var2.start && this.length == var2.length && this.type.equals(var2.type);
   }

   @Override
   public int hashCode() {
      return (int)this.start;
   }

   @Override
   public String toString() {
      return this.pairValue == 0
         ? Strings.ff("%s (%d, %d)", this.type, this.start, this.length)
         : Strings.ff("%s (%d, %d) (%d)", this.type, this.start, this.length, this.pairValue);
   }

   public int compareTo(Token var1) {
      if (this.start != var1.start) {
         return (int)(this.start - var1.start);
      } else {
         return this.length != var1.length ? this.length - var1.length : this.type.compareTo(var1.type);
      }
   }

   public long end() {
      return this.start + this.length;
   }
}
