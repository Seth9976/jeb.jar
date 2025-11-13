package com.pnfsoftware.jeb.core.units.code.asm.type;

import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Lists;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jebglobal.aye;
import com.pnfsoftware.jebglobal.ays;
import com.pnfsoftware.jebglobal.ayy;
import com.pnfsoftware.jebglobal.aza;
import com.pnfsoftware.jebglobal.azh;
import com.pnfsoftware.jebglobal.azj;
import com.pnfsoftware.jebglobal.azl;
import com.pnfsoftware.jebglobal.azn;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class TypeStringParser {
   private static final ILogger logger = GlobalLog.getLogger(TypeStringParser.class);
   private static final TypeStringParser verifier = new TypeStringParser();
   public static final int TYPE = 0;
   public static final int PROTO = 1;
   public static final int SIG = 2;
   public static final int DECL = 3;
   private ayy typeman;
   private BailErrorStrategy errorHandler = new TypeStringParser$1(this);

   private TypeStringParser() {
   }

   public TypeStringParser(ITypeManager var1) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide a type manager");
      } else {
         this.typeman = (ayy)var1;
      }
   }

   public INativeType parseType(String var1) throws TypeStringParseException {
      aye var2;
      try {
         var1 = massageForType(var1);
         var2 = this.parseForType(var1);
      } catch (Exception var4) {
         throw new TypeStringParseException(var4);
      }

      if (var2 == null) {
         throw new TypeStringParseException();
      } else {
         return var2;
      }
   }

   public TypeStringParser.Decl parseDeclaration(String var1) throws TypeStringParseException {
      return this.parseDeclaration(var1, false);
   }

   public TypeStringParser.Decl parseDeclaration(String var1, boolean var2) throws TypeStringParseException {
      azh var3;
      try {
         var1 = massageForDeclaration(var1);
         var3 = this.parseSingleDeclaration(var1);
      } catch (Exception var5) {
         throw new TypeStringParseException(var5);
      }

      if (var3 == null) {
         throw new TypeStringParseException();
      } else {
         return new TypeStringParser.Decl(var3.pC(), var3.A());
      }
   }

   @Deprecated
   public IPrototypeItem parsePrototype(String var1) throws TypeStringParseException {
      IPrototypeItem var2 = null;
      String[] var3 = new String[1];

      try {
         var1 = massageProtoString(var1, var3);
         if (var1 != null) {
            var2 = (IPrototypeItem)this.parseForType(var1);
         }
      } catch (Exception var7) {
         throw new TypeStringParseException("Error parsing prototype: " + var1, var7);
      }

      if (var2 == null) {
         throw new TypeStringParseException("Error parsing prototype: " + var1);
      } else {
         String var4 = var3[0];
         if (!Strings.isBlank(var4)) {
            CallingConventionName var5 = CallingConventionName.find(var4);
            if (var5 == null) {
               throw new TypeStringParseException("Unknown calling convention: " + var4);
            }

            var4 = var5.toString();
            ICallingConvention var6 = this.typeman.A().getConvention(var4);
            if (var6 == null) {
               throw new TypeStringParseException("Unsupported calling convention: " + var4);
            }

            ((ays)var2).pC(var6);
         }

         return var2;
      }
   }

   private static String massageProtoString(String var0, String[] var1) {
      String var2 = Strings.trimWhitespaces(var0);
      if (var2.endsWith(";")) {
         var2 = var2.substring(0, var2.length() - 1);
      }

      if (!var2.isEmpty() && var2.charAt(var2.length() - 1) == ')') {
         int var3 = var2.indexOf(40);
         if (var3 < 0) {
            return null;
         } else {
            int var4 = var2.lastIndexOf(41);
            if (var4 < 0) {
               return null;
            } else {
               String var5 = var2.substring(var3 + 1, var4).trim();
               String var6 = var2.substring(0, var3);
               String var7 = "";
               int var10 = 0;
               if (var6.startsWith("<")) {
                  var10 = var6.indexOf(62);
                  if (var10 < 0) {
                     return null;
                  }

                  var7 = var6.substring(1, var10);
                  if (var1 != null) {
                     var1[0] = var7;
                     var7 = "";
                  }

                  var10++;
               }

               String var9 = var6.substring(var10);
               return var9 + " " + var7 + " f(" + var5 + ");";
            }
         }
      } else {
         return null;
      }
   }

   public IPrototypeItem parseSignature(String var1) throws TypeStringParseException {
      return this.parseSignature(var1, false);
   }

   public IPrototypeItem parseSignature(String var1, boolean var2) throws TypeStringParseException {
      var1 = massageSingleDeclaration(var1, var2);

      azh var3;
      try {
         var3 = this.parseSingleDeclaration(var1);
      } catch (Exception var5) {
         throw new TypeStringParseException("Error parsing signature: " + var1, var5);
      }

      if (var3 == null) {
         throw new TypeStringParseException("Error parsing signature: " + var1);
      } else if (!(var3.pC() instanceof ays)) {
         throw new TypeStringParseException();
      } else {
         return (ays)var3.pC();
      }
   }

   private aye parseForType(String var1) {
      return this.parseSingleDeclaration(var1).pC();
   }

   private azh parseSingleDeclaration(String var1) {
      return (azh)Lists.get(this.parseInternal(var1, false), 0);
   }

   public List parseTypesRaw(String var1) throws TypeStringParseException {
      try {
         return (List)this.parseInternal(var1, true).stream().map(var0 -> var0.pC()).collect(Collectors.toList());
      } catch (Exception var3) {
         throw new TypeStringParseException("Error parsing types", var3);
      }
   }

   private List parseInternal(String var1, boolean var2) {
      azl var3 = new azl(CharStreams.fromString(var1));
      CommonTokenStream var4 = new CommonTokenStream(var3);
      azn var5 = new azn(var4);
      var5.setErrorHandler(this.errorHandler);
      azn.RC var6 = var5.ZD();
      if (this.typeman == null) {
         return null;
      } else {
         aza var8 = new aza(1, var2);
         azj var9 = new azj(var6, var8, this.typeman);
         return var9.wS();
      }
   }

   public static void verify(int var0, String var1, boolean var2) throws TypeStringParseException {
      String var3 = Strings.trimWhitespaces(var1);
      if (var2) {
         var3 = removeCppTemplates(var3);
         if (var3 == null) {
            throw new TypeStringParseException("Illegal template in signature: " + var3);
         }
      }

      if (var0 == 0) {
         var3 = massageForType(var3);
      }

      if (!var3.endsWith(";")) {
         var3 = var3 + ";";
      }

      try {
         verifier.parseSingleDeclaration(var3);
      } catch (Exception var5) {
         throw new TypeStringParseException("Error parsing signature: " + var3, var5);
      }
   }

   public static void verifyType(String var0, boolean var1) throws TypeStringParseException {
      verify(0, var0, var1);
   }

   public static void verifyDeclaration(String var0, boolean var1) throws TypeStringParseException {
      verify(3, var0, var1);
   }

   public static void verifySignature(String var0, boolean var1) throws TypeStringParseException {
      verify(2, var0, var1);
   }

   public static String massageForType(String var0) {
      var0 = Strings.trimWhitespaces(var0);
      if (var0.endsWith(";")) {
         return var0;
      } else if (var0.endsWith(")")) {
         return var0 + ";";
      } else if (Strings.contains(var0, "(", ")", ",")) {
         return var0;
      } else {
         int var1 = var0.indexOf(91);
         return var1 >= 0 ? var0.substring(0, var1) + " _" + var0.substring(var1) + ";" : var0 + " _;";
      }
   }

   public static String massageForDeclaration(String var0) {
      var0 = Strings.trimWhitespaces(var0);
      if (!var0.endsWith(";")) {
         var0 = var0 + ";";
      }

      return var0;
   }

   public static String massageSingleDeclaration(String var0, boolean var1) throws TypeStringParseException {
      String var2 = Strings.trimWhitespaces(var0);
      if (!var2.endsWith(";")) {
         var2 = var2 + ";";
      }

      if (var1) {
         var2 = removeCppTemplates(var2);
         if (var2 == null) {
            throw new TypeStringParseException("Illegal template in signature: " + var2);
         }
      }

      return var2;
   }

   public static String removeCppTemplates(String var0) {
      int var1 = 0;
      ArrayList var2 = new ArrayList();
      int var3 = 0;

      for (int var4 = 0; var4 < var0.length(); var4++) {
         char var5 = var0.charAt(var4);
         if (var5 == '<') {
            if (var1 == 0) {
               var3 = var4;
            }

            var1++;
         } else if (var5 == '>') {
            if (--var1 < 0) {
               return null;
            }

            if (var1 == 0) {
               var2.add(new Couple(var3, var4 + 1));
            }
         }
      }

      if (var1 != 0) {
         return null;
      } else {
         for (int var6 = var2.size() - 1; var6 >= 0; var6--) {
            Couple var7 = (Couple)var2.get(var6);
            var0 = var0.substring(0, (Integer)var7.getFirst()) + var0.substring((Integer)var7.getSecond(), var0.length());
         }

         return var0;
      }
   }

   public static class Decl {
      INativeType type;
      String name;

      Decl(INativeType var1, String var2) {
         if (var1 != null && var2 != null) {
            this.type = var1;
            this.name = var2;
         } else {
            throw new IllegalArgumentException();
         }
      }

      public INativeType getType() {
         return this.type;
      }

      public String getName() {
         return this.name;
      }

      public boolean isGenericName() {
         return this.name.equals("__ANONYMOUS__");
      }

      @Override
      public String toString() {
         return this.type.getSignature() + " " + this.getName() + ";";
      }
   }
}
