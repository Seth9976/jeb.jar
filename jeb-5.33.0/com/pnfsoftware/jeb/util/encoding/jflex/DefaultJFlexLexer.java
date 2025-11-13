package com.pnfsoftware.jeb.util.encoding.jflex;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

public abstract class DefaultJFlexLexer {
   protected long tokenStart;
   protected int tokenLength;
   protected int offset;

   protected Token token(TokenType var1, int var2, int var3, int var4, int var5) {
      this.tokenStart = var4;
      this.tokenLength = var5;
      return new Token(var1, var2 + this.offset, var3);
   }

   protected Token token(TokenType var1, long var2, int var4) {
      return new Token(var1, var2 + this.offset, var4);
   }

   protected Token token(TokenType var1) {
      return new Token(var1, this.yychar() + this.offset, this.yylength());
   }

   protected Token token(TokenType var1, int var2) {
      return new Token(var1, this.yychar() + this.offset, this.yylength(), (byte)var2);
   }

   public List parse(String var1) throws IOException {
      StringReader var2 = new StringReader(var1);
      ArrayList var3 = new ArrayList();
      this.parse(var2, var3);
      return var3;
   }

   public void parse(Reader var1, List var2) throws IOException {
      this.yyreset(var1);
      this.offset = 0;

      for (Token var3 = this.yylex(); var3 != null; var3 = this.yylex()) {
         var2.add(var3);
      }
   }

   public abstract void yyreset(Reader var1);

   public abstract Token yylex() throws IOException;

   public abstract char yycharat(int var1);

   public abstract int yylength();

   public abstract String yytext();

   public abstract long yychar();
}
