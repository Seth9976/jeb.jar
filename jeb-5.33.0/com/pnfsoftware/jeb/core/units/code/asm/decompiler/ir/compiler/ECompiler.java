package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.core.units.code.CodePointer;
import com.pnfsoftware.jeb.core.units.code.asm.analyzer.BranchTarget;
import com.pnfsoftware.jeb.core.units.code.asm.cfg.CFG;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IEGlobalContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.IERoutineContext;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.EUtil;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEAssign;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IECall;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEGeneric;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEImm;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEJump;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEMem;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IERange;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEStatement;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IESwitch;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IEVar;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardPrototype;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardType;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.IWildcardTypeManager;
import com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.OperationType;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.IPrototypeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.io.EndianUtil;
import com.pnfsoftware.jeb.util.io.IO;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ECompiler {
   private static final ILogger logger = GlobalLog.getLogger(ECompiler.class);
   private IEGlobalContext gctx;
   private Map labelmap = new HashMap();
   private static final int CANARY_BASE = 1879048192;
   private int currentCanaryOffset = 1879048192;
   private Map labelToCanary = new HashMap();
   private Map canaryToLabel = new HashMap();
   private static Set trgOperators = Sets.createNonNulls('+', '-', '*', '/', '%', '&', '|', '^', '~', '<', '>', '=', '!', '#');
   private static Set stdStoppers = Sets.createNonNulls(' ', '(', ')', '[', ']', ':', ',', ';');
   private static Set stdStoppersWithOpTriggers = new HashSet(stdStoppers);
   private static Set digits = Sets.createNonNulls('0', '1', '2', '3', '4', '5', '6', '7', '8', '9');
   private static Set hexchars = Sets.createNonNulls('0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F');
   private static Set tokenchars = Sets.createNonNulls(
      '_',
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      'a',
      'b',
      'c',
      'd',
      'e',
      'f',
      'g',
      'h',
      'i',
      'j',
      'k',
      'l',
      'm',
      'n',
      'o',
      'p',
      'q',
      'r',
      's',
      't',
      'u',
      'v',
      'w',
      'x',
      'y',
      'z',
      'A',
      'B',
      'C',
      'D',
      'E',
      'F',
      'G',
      'H',
      'I',
      'J',
      'K',
      'L',
      'M',
      'N',
      'O',
      'P',
      'Q',
      'R',
      'S',
      'T',
      'U',
      'V',
      'W',
      'X',
      'Y',
      'Z'
   );

   public static IEGeneric cc(String var0, IEGlobalContext var1) {
      return cc(var0, var1, IEGeneric.class);
   }

   public static IEGeneric cc(String var0, IEGlobalContext var1, Class var2) {
      ECompiler var3 = new ECompiler(var1);

      try {
         return var3.compileStatement(var0).getStatement();
      } catch (IllegalArgumentException var4) {
         return var3.compileExpression(var0).getExpression();
      }
   }

   public ECompiler(IEGlobalContext var1) {
      this.gctx = var1;
   }

   public void reset() {
      this.labelmap.clear();
      this.currentCanaryOffset = 1879048192;
      this.labelToCanary.clear();
      this.canaryToLabel.clear();
   }

   public ECompiler.CompiledExpression compileExpression(String var1) {
      return this.compileExpression(null, var1);
   }

   public ECompiler.CompiledExpression compileExpression(IERoutineContext var1, String var2) {
      if (var1 == null) {
         var1 = this.gctx.createRoutineContext();
      }

      ECompiler.InternalCompiler var3 = new ECompiler.InternalCompiler(var1, var2);
      ECompiler.Parsed var4 = var3.parse();
      if (var4.result instanceof IEStatement) {
         throw new IllegalArgumentException("Expected a non-statement");
      } else {
         return new ECompiler.CompiledExpression(var1, var4.result);
      }
   }

   public ECompiler.CompiledStatement compileStatement(String var1) {
      return this.compileStatement(null, var1);
   }

   public ECompiler.CompiledStatement compileStatement(IERoutineContext var1, String var2) {
      CFG var3 = this.compileCfg(var1, var2);
      if (var3.getInstructionCount() != 1) {
         throw new IllegalArgumentException("The provided input string do not fit in a single instruction");
      } else {
         return new ECompiler.CompiledStatement((IEStatement)var3.getInstruction(0L));
      }
   }

   public CFG compileCfg(String... var1) {
      return this.compileCfg(null, var1);
   }

   public CFG compileCfg(IERoutineContext var1, String... var2) {
      return (CFG)this.compile(var1, false, 0L, Arrays.asList(var2), 0, var2.length).getSecond();
   }

   public ECompiler.CompiledRoutine compileRoutine(String... var1) {
      return this.compileProc(Arrays.asList(var1), 0, var1.length);
   }

   public ECompiler.CompiledProgram compileProgram(File var1) throws IOException {
      List var2 = IO.readLines(var1);
      return this.compileProgram(var2);
   }

   public ECompiler.CompiledProgram compileProgram(String... var1) {
      return this.compileProgram(Arrays.asList(var1));
   }

   public ECompiler.CompiledProgram compileProgram(List var1) {
      ECompiler.CompiledProgram var2 = new ECompiler.CompiledProgram();
      boolean var3 = false;
      int var4 = -1;

      for (int var5 = 0; var5 < var1.size(); var5++) {
         String var6 = (String)var1.get(var5);
         var6 = trimComment(var6);
         if (!Strings.isBlank(var6)) {
            if (var6.startsWith("PROC")) {
               if (var3) {
                  throwParsingException("Unexpected PROC inside PROC", var6);
               }

               var3 = true;
               var4 = var5;
            } else if (var6.startsWith("ENDP")) {
               if (!var3) {
                  throwParsingException("Unexpected ENDP outside PROC", var6);
               }

               ECompiler.CompiledRoutine var7 = this.compileProc(var1, var4, var5 + 1);
               var2.addRoutine(var7);
               var3 = false;
               var4 = -1;
            } else if (var6.startsWith("D")) {
               if (var3) {
                  throwParsingException("Cannot declare data inside a PROC", var6);
               }

               ECompiler.CompiledField var9 = this.compileField(var6);
               var2.addField(var9);
            } else if (!var3) {
               throwParsingException("Unknown / Directive not supported", var6);
            }
         }
      }

      return var2;
   }

   private static String trimComment(String var0) {
      if (var0.trim().startsWith("//")) {
         return "";
      } else {
         int var1 = var0.indexOf(";");
         if (var1 >= 0) {
            var0 = var0.substring(0, var1);
         }

         return var0;
      }
   }

   private ECompiler.CompiledField compileField(String var1) {
      ECompiler.CompiledField var2 = new ECompiler.CompiledField();
      String var3 = trimComment(var1);
      if (Strings.isBlank(var3)) {
         throwParsingException("Empty line or comment line", var3);
      }

      int var4 = skipSpaces(var3, 0);
      if (var3.startsWith("DB", var4) || var3.startsWith("DW", var4) || var3.startsWith("DD", var4) || var3.startsWith("DQ", var4)) {
         char var48 = Character.toUpperCase(var3.charAt(var4 + 1));
         var4 = mustSkipSpacesOrEol(var3, var4 + 2);
         int var42 = scanUntilStopper(var3, var4);
         String var51 = var3.substring(var4, var42);
         if (!var51.startsWith("@")) {
            throwParsingException("Expected an @ character to prefix the native address", var3, var4);
         }

         long var54 = Long.parseLong(var3.substring(var4 + 1, var42), 16);
         var2.address = var54;
         var4 = mustSkipSpacesOrEol(var3, var42);
         if (var4 == var3.length()) {
            throwParsingException("Missing data value", var3, var4);
         }

         char var56 = var3.charAt(var4);
         if (var48 == 'B' && (var56 == '\'' || var56 == '"')) {
            if (var56 == '\'') {
               var42 = scanUntil(var3, ++var4, false, '\'');
               var2.value = Formatter.hexStringToByteArray(var3, var4, var42);
               var4 = var42 + 1;
            } else if (var56 == '"') {
               var42 = scanString(var3, var4);

               try {
                  String var58 = Formatter.unescapeString(var3.substring(var4 + 1, var42));
                  var2.value = var58.getBytes("US-ASCII");
               } catch (Exception var13) {
                  throwParsingException("Illegal DS string", var3, var4);
               }
            }

            if (var2.value == null) {
               throwParsingException("Illegal value for DS", var3, var4);
            }
         } else {
            var42 = scanUntilStopper(var3, var4);
            Long var57 = Conversion.stringToLongUnsafe(var3.substring(var4, var42));
            if (var57 == null) {
               throwParsingException("Illegal number value", var3, var4);
            }

            switch (var48) {
               case 'B':
                  var2.value = new byte[]{var57.byteValue()};
                  break;
               case 'D':
                  if (this.gctx.isBigEndian()) {
                     var2.value = EndianUtil.intToBEBytes(var57.intValue());
                  } else {
                     var2.value = EndianUtil.intToLEBytes(var57.intValue());
                  }
                  break;
               case 'Q':
                  if (this.gctx.isBigEndian()) {
                     var2.value = EndianUtil.longToBEBytes(var57);
                  } else {
                     var2.value = EndianUtil.longToLEBytes(var57);
                  }
                  break;
               case 'W':
                  if (this.gctx.isBigEndian()) {
                     var2.value = EndianUtil.shortToBEBytes(var57.shortValue());
                  } else {
                     var2.value = EndianUtil.shortToLEBytes(var57.shortValue());
                  }
                  break;
               default:
                  throwParsingException("Unknown data declarator", var3, var4);
            }
         }
      } else if (var3.startsWith("DV", var4)) {
         var4 = mustSkipSpacesOrEol(var3, var4 + 2);
         int var5 = scanUntilStopper(var3, var4);
         String var6 = var3.substring(var4, var5);
         if (var6.startsWith("@")) {
            throwParsingException("@ character not allowed in variable name", var3, var4);
         }

         var2.name = var6;
         var4 = mustSkipSpacesOrEol(var3, var5);
         var5 = scanUntilStopper(var3, var4);
         String var7 = var3.substring(var4, var5);
         if (!var7.startsWith("@")) {
            throwParsingException("Expected an @ character to prefix the native address", var3, var4);
         }

         var2.address = Long.parseLong(var3.substring(var4 + 1, var5), 16);
         var4 = mustSkipSpacesOrEol(var3, var5);
         if (var3.charAt(var4) != ':') {
            throwParsingException("Expected a type prefixed by :", var3, var4);
         }

         var5 = scanUntilStopper(var3, ++var4);
         String var8 = var3.substring(var4, var5);
         INativeType var9 = TypeUtil.buildQuickType(this.gctx.getWildcardTypeManager().getNativeTypeManager(), var8);
         if (var9 == null) {
            throwParsingException(Strings.ff("Cannot parse type string: '%s'", var8), var3, var4);
         }

         var2.type = var9;
         var4 = mustSkipSpacesOrEol(var3, var5);
         if (var4 < var3.length()) {
            if (var3.charAt(var4) != '\'') {
               throwParsingException("Expected a hex-encoded string enclosed in single-quotes", var3, var4);
            }

            var5 = scanUntil(var3, ++var4, false, '\'');
            String var10 = var3.substring(var4, var5).replace(" ", "");
            if (var10.length() % 2 != 0) {
               throwParsingException("Illegal hex-encoded string (odd length)", var3, var4);
            }

            byte[] var11 = Formatter.hexStringToByteArray(var10);
            if (var11.length > var9.getSize()) {
               throwParsingException(Strings.ff("%d bytes provided when the type specifies %d bytes!", var11.length, var9.getSize()), var3, var4);
            }

            var2.value = var11;
         }
      } else if (var3.startsWith("DS", var4)) {
         var4 = mustSkipSpacesOrEol(var3, var4 + 2);
         int var35 = scanUntilStopper(var3, var4);
         String var46 = var3.substring(var4, var35);
         if (var46.startsWith("@")) {
            throwParsingException("@ character not allowed in variable name", var3, var4);
         }

         var2.name = var46;
         var4 = mustSkipSpacesOrEol(var3, var35);
         var35 = scanUntilStopper(var3, var4);
         String var49 = var3.substring(var4, var35);
         if (!var49.startsWith("@")) {
            throwParsingException("Expected an @ character to prefix the native address", var3, var4);
         }

         var2.address = Long.parseLong(var3.substring(var4 + 1, var35), 16);
         var4 = mustSkipSpacesOrEol(var3, var35);
         if (var3.charAt(var4) != '"') {
            throwParsingException("String variable data must be enclosed in double-quotes", var3, var4);
         }

         var35 = scanString(var3, var4);

         try {
            var2.stringValue = Formatter.unescapeString(var3.substring(var4 + 1, var35));
            byte[] var52 = var2.stringValue.getBytes("US-ASCII");
            var2.value = Arrays.copyOf(var52, var52.length + 1);
         } catch (Exception var12) {
            throwParsingException("Illegal DS string", var3, var4);
         }

         var2.type = TypeUtil.buildArrayType(this.gctx.getWildcardTypeManager().getNativeTypeManager(), "char", var2.value.length);
      } else if (var3.startsWith("DR", var4)) {
         var4 = mustSkipSpacesOrEol(var3, var4 + 2);
         int var38 = scanUntilStopper(var3, var4);
         String var47 = var3.substring(var4, var38);
         if (var47.startsWith("@")) {
            throwParsingException("@ character not allowed in reference name", var3, var4);
         }

         var2.name = var47;
         var4 = mustSkipSpacesOrEol(var3, var38);
         var38 = scanUntilStopper(var3, var4);
         String var50 = var3.substring(var4, var38);
         if (!var50.startsWith("@")) {
            throwParsingException("Expected an @ character to prefix the native address", var3, var4);
         }

         var2.address = Long.parseLong(var3.substring(var4 + 1, var38), 16);
         var4 = mustSkipSpacesOrEol(var3, var38);
         if (var3.charAt(var4) != '&') {
            throwParsingException("Referenced name must start with ampersand", var3, var4);
         }

         var38 = scanUntilStopper(var3, ++var4);
         var2.impname = var3.substring(var4, var38);
         var4 = mustSkipSpacesOrEol(var3, var38);
         if (var4 != var3.length()) {
            if (var3.charAt(var4) != ':') {
               throwParsingException("Expected a type prefixed by :", var3, var4);
            }

            var38 = scanUntilSpace(var3, ++var4);
            String var53 = var3.substring(var4, var38);
            INativeType var55 = TypeUtil.buildQuick(this.gctx.getWildcardTypeManager().getNativeTypeManager(), var53);
            if (var55 == null) {
               throwParsingException(Strings.ff("Cannot parse type string: '%s'", var53), var3, var4);
            }

            var2.imptype = var55;
         }
      } else {
         throwParsingException("Unsupported data declarator", var3, var4);
      }

      return var2;
   }

   private ECompiler.CompiledRoutine compileProc(List var1, int var2, int var3) {
      long var4 = 0L;
      String var6 = null;
      IPrototypeItem var7 = null;
      String var8 = (String)var1.get(var2);
      var8 = trimComment(var8);
      int var9 = skipSpaces(var8, 0);
      if (var8.startsWith("PROC", var9)) {
         var9 = mustSkipSpacesOrEol(var8, var9 + 4);
         if (var9 < var8.length()) {
            char var11 = var8.charAt(var9);
            if (var11 != '@' && var11 != ':') {
               int var10 = scanUntilStopper(var8, var9);
               var6 = var8.substring(var9, var10);
               var9 = mustSkipSpacesOrEol(var8, var10);
            }

            if (var9 < var8.length()) {
               if (var8.charAt(var9) == '@') {
                  int var20 = scanHexInt(var8, ++var9);
                  var4 = Long.parseLong(var8.substring(var9, var20), 16);
                  var9 = mustSkipSpacesOrEol(var8, var20);
               }

               if (var9 < var8.length()) {
                  if (var8.charAt(var9) == ':') {
                     String var12 = var8.substring(++var9);
                     var7 = TypeUtil.buildQuickPrototype(this.gctx.getWildcardTypeManager().getNativeTypeManager(), var12);
                     if (var7 == null) {
                        throwParsingException(Strings.ff("Could not parse prototype: '%s'", var12), var8, var9);
                     }
                  } else {
                     throwParsingException("Unknown token in procedure declaration", var8, var9);
                  }
               }
            }
         }

         var8 = (String)var1.get(var3 - 1);
         var8 = trimComment(var8);
         var9 = skipSpaces(var8, 0);
         if (!var8.startsWith("ENDP", var9)) {
            throw new RuntimeException("Expected ENDP");
         }

         mustSkipSpacesOrEol(var8, var9 + 4);
         var2++;
         var3--;
      }

      IERoutineContext var21 = (IERoutineContext)this.compile(null, true, var4, var1, var2, var3).getFirst();
      ECompiler.CompiledRoutine var22 = new ECompiler.CompiledRoutine(var21);
      var22.address = var4;
      var22.name = var6;
      var22.proto = var7;
      return var22;
   }

   private Couple compile(IERoutineContext var1, boolean var2, long var3, List var5, int var6, int var7) {
      this.reset();
      if (var1 == null) {
         var1 = this.gctx.createRoutineContext();
      }

      int var8 = 0;
      long var9 = var3;
      ArrayList var11 = new ArrayList();

      for (String var13 : var5.subList(var6, var7)) {
         String var14 = trimComment(var13);
         ECompiler.InternalCompiler var15 = new ECompiler.InternalCompiler(var1, var14);
         ECompiler.Parsed var16 = var15.parseStatement(var8, var9);
         if (var16 != null) {
            IEStatement var17 = (IEStatement)var16.result;
            var17.setSize(var16.requestedStatementSize);
            var11.add(var17);
            if (var16.requestedNativeAddress != null) {
               if (var11.size() == 1 && var9 != var16.requestedNativeAddress) {
                  throwParsingException(
                     Strings.ff("Entry instruction specifies native address 0x%X -- it should be 0x%X", var16.requestedNativeAddress, var9), var14
                  );
               }

               if (var16.requestedNativeAddress < var9) {
                  throwParsingException(
                     Strings.ff("Instruction specifies native address 0x%X -- it should be at least 0x%X", var16.requestedNativeAddress, var9), var14
                  );
               }

               var9 = var16.requestedNativeAddress;
            }

            var17.setLowerLevelAddress(var9);
            var8 += var17.getSize();
            var9++;
         }
      }

      for (IEStatement var22 : var11) {
         if (var22 instanceof IEJump var24) {
            int var27 = var24.getBranchAddress();
            if (var27 >= 1879048192) {
               String var30 = (String)this.canaryToLabel.get(var27);
               Integer var33 = (Integer)this.labelmap.get(var30);
               Assert.a(var33 != null, "Cannot resolve if-statement target for label: " + var30);
               var24.setBranchAddress(var33);
            }
         } else if (var22 instanceof IESwitch var23) {
            int var25 = var23.getDefaultAddress();
            if (var25 >= 1879048192) {
               String var28 = (String)this.canaryToLabel.get(var25);
               Integer var31 = (Integer)this.labelmap.get(var28);
               Assert.a(var31 != null, "Cannot resolve switch default target for label: " + var28);
               var23.setDefaultAddress(var31);
            }

            for (Couple var32 : var23.getCases()) {
               var25 = (Integer)var32.getSecond();
               if (var25 >= 1879048192) {
                  String var18 = (String)this.canaryToLabel.get(var25);
                  Integer var19 = (Integer)this.labelmap.get(var18);
                  Assert.a(var19 != null, "Cannot resolve switch case target for label: " + var18);
                  var32.setSecond(var19);
               }
            }
         }
      }

      CFG var21;
      if (!var2) {
         var21 = new CFG(var11, null, null, 0L, 0L, 2);
      } else {
         var1.setStatements(var11, true, false, true);
         this.gctx.addRoutineContext(var1);
         var21 = var1.getCfg();
      }

      return new Couple(var1, var21);
   }

   private static void throwParsingException(String var0, String var1) {
      throwParsingException(var0, var1, -1);
   }

   private static void throwParsingException(String var0, String var1, int var2) {
      var0 = Strings.ff("Parsing error: %s", var0);
      if (var2 >= 0) {
         var0 = var0 + Strings.ff(":\n  %s\n  %s^", var1, Strings.generate(' ', var2));
      } else {
         var0 = var0 + Strings.ff(":\n  %s", var1);
      }

      throw new IllegalStateException(var0);
   }

   private static boolean isContained(char var0, char... var1) {
      for (char var5 : var1) {
         if (var0 == var5) {
            return true;
         }
      }

      return false;
   }

   private static int scanUntil(String var0, int var1, boolean var2, Set var3) {
      while (var1 < var0.length()) {
         char var4 = var0.charAt(var1);
         if (!(var2 ^ var3.contains(var4))) {
            var1++;
            continue;
         }
         break;
      }

      return var1;
   }

   private static int scanUntil(String var0, int var1, boolean var2, char... var3) {
      while (var1 < var0.length()) {
         char var4 = var0.charAt(var1);
         if (!(var2 ^ isContained(var4, var3))) {
            var1++;
            continue;
         }
         break;
      }

      return var1;
   }

   private static int scanUntilStopper(String var0, int var1) {
      return scanUntil(var0, var1, false, stdStoppers);
   }

   private static int scanUntilStopperOrOperatorTrigger(String var0, int var1) {
      return scanUntil(var0, var1, false, stdStoppersWithOpTriggers);
   }

   private static int scanUntilSpace(String var0, int var1) {
      return scanUntil(var0, var1, false, (char)32);
   }

   private static int scanInt(String var0, int var1) {
      return scanUntil(var0, var1, true, digits);
   }

   private static int scanHexInt(String var0, int var1) {
      return scanUntil(var0, var1, true, hexchars);
   }

   private static int scanToken(String var0, int var1) {
      return scanUntil(var0, var1, true, tokenchars);
   }

   private static int skipSpaces(String var0, int var1) {
      while (var1 < var0.length()) {
         char var2 = var0.charAt(var1);
         if (var2 == ' ') {
            var1++;
            continue;
         }
         break;
      }

      return var1;
   }

   private static int mustSkipSpacesOrEol(String var0, int var1) {
      int var2 = skipSpaces(var0, var1);
      if (var2 == var1 && var2 != var0.length()) {
         throwParsingException("Expected a space separator before next token or EOL", var0, var1);
      }

      return var2;
   }

   private static int scanString(String var0, int var1) {
      char var2 = var0.charAt(var1++);
      Assert.a(var2 == '"');

      while (var1 < var0.length()) {
         var2 = var0.charAt(var1++);
         if (var2 == '\\') {
            var1++;
         }

         if (var2 == '"') {
            var1--;
            break;
         }
      }

      return var1;
   }

   static {
      stdStoppersWithOpTriggers.addAll(trgOperators);
   }

   public static class CompiledExpression {
      IERoutineContext ctx;
      IEGeneric expr;

      public CompiledExpression(IERoutineContext var1, IEGeneric var2) {
         this.ctx = var1;
         this.expr = var2;
      }

      public IERoutineContext getContext() {
         return this.ctx;
      }

      public IEGeneric getExpression() {
         return this.expr;
      }
   }

   public static class CompiledField {
      long address;
      String name;
      INativeType type;
      byte[] value;
      String stringValue;
      String impname;
      INativeType imptype;

      public boolean isRawBytes() {
         return this.type == null && this.value != null;
      }

      public boolean isRegularVariable() {
         return this.name != null && this.type != null && this.impname == null && this.stringValue == null;
      }

      public boolean isStringVariable() {
         return this.name != null && this.type != null && this.impname == null && this.stringValue != null;
      }

      public boolean isImportedVariable() {
         return this.name != null && this.type == null && this.impname != null && this.value == null;
      }

      public long getAddress() {
         return this.address;
      }

      public String getName() {
         return this.name;
      }

      public INativeType getType() {
         return this.type;
      }

      public int getSize() {
         return this.value.length;
      }

      public byte[] getBytes() {
         return this.value;
      }

      public String getNameOfImport() {
         return this.impname;
      }

      public INativeType getTypeOfImport() {
         return this.imptype;
      }

      public String getStringValue() {
         return this.stringValue;
      }

      @Override
      public String toString() {
         if (this.isRegularVariable()) {
            return this.name
               + "{"
               + this.type.getName()
               + "}@"
               + Formatter.toHexString(this.address, true)
               + (this.value != null ? "=" + Formatter.byteArrayToHex(this.value) : "");
         } else if (this.isStringVariable()) {
            return "@" + Formatter.toHexString(this.address, true) + "=\"" + this.stringValue + "\"";
         } else if (this.isImportedVariable()) {
            return this.name
               + "@"
               + Formatter.toHexString(this.address, true)
               + "->"
               + this.impname
               + (this.imptype != null ? "{" + this.imptype.getName() + "}" : "");
         } else {
            return this.isRawBytes() ? "@" + Formatter.toHexString(this.address, true) + "=" + Formatter.byteArrayToHex(this.value) : super.toString();
         }
      }
   }

   public static class CompiledProgram {
      private Set namesInUse = new HashSet();
      private Set addressesInUse = new HashSet();
      private List routines = new ArrayList();
      private List fields = new ArrayList();

      private void verify(String var1, Long var2) {
         if (var1 != null && !this.namesInUse.add(var1)) {
            throw new RuntimeException("Routine: name conflict: " + var1);
         } else if (var2 != null && !this.addressesInUse.add(var2)) {
            throw new RuntimeException(Strings.ff("Routine: address conflict: 0x%X", var2));
         }
      }

      void addRoutine(ECompiler.CompiledRoutine var1) {
         this.verify(var1.getName(), var1.getAddress());
         this.routines.add(var1);
      }

      public List getRoutines() {
         return this.routines;
      }

      public ECompiler.CompiledRoutine getRoutine(int var1) {
         return (ECompiler.CompiledRoutine)this.routines.get(var1);
      }

      void addField(ECompiler.CompiledField var1) {
         this.verify(var1.getName(), var1.getAddress());
         this.fields.add(var1);
      }

      public List getFields() {
         return this.fields;
      }

      public ECompiler.CompiledField getField(int var1) {
         return (ECompiler.CompiledField)this.fields.get(var1);
      }
   }

   public static class CompiledRoutine {
      String name;
      long address;
      IPrototypeItem proto;
      IERoutineContext ctx;

      public CompiledRoutine(IERoutineContext var1) {
         this.ctx = var1;
      }

      public String getName() {
         return this.name;
      }

      public long getAddress() {
         return this.address;
      }

      public IPrototypeItem getPrototype() {
         return this.proto;
      }

      public IERoutineContext getContext() {
         return this.ctx;
      }
   }

   public static class CompiledStatement {
      IEStatement stm;

      public CompiledStatement(IEStatement var1) {
         this.stm = var1;
      }

      public IERoutineContext getContext() {
         return this.stm.getContext();
      }

      public IEStatement getStatement() {
         return this.stm;
      }
   }

   static enum ExpressionType {
      ANY,
      CONDITIONAL,
      COMPOSITION,
      SEGVAR;
   }

   private class InternalCompiler {
      IERoutineContext ectx;
      ECompiler.InternalCompiler parent;
      int parentBegin;
      private final String s;
      private Integer irOffset;
      private long currentNativeAddress;

      InternalCompiler(IERoutineContext var2, String var3) {
         this.ectx = var2;
         this.s = Strings.rtrim(var3);
      }

      private InternalCompiler(ECompiler.InternalCompiler var2, int var3, int var4) {
         this.parent = var2;
         this.parentBegin = var3;
         this.ectx = var2.ectx;
         this.s = var2.s.substring(var3, var4);
      }

      private InternalCompiler(ECompiler.InternalCompiler var2, int var3) {
         this.parent = var2;
         this.parentBegin = var3;
         this.ectx = var2.ectx;
         this.s = var2.s.substring(var3);
      }

      int getIROffset() {
         ECompiler.InternalCompiler var1 = this;

         while (var1.irOffset == null) {
            var1 = var1.parent;
         }

         return var1.irOffset;
      }

      long getNativeAddress() {
         ECompiler.InternalCompiler var1 = this;

         while (var1.irOffset == null) {
            var1 = var1.parent;
         }

         return var1.currentNativeAddress;
      }

      ECompiler.Parsed parseStatement(int var1, long var2) {
         if (this.irOffset != null) {
            throw new IllegalStateException("Already parsing a statement");
         } else {
            this.irOffset = var1;
            this.currentNativeAddress = var2;
            int var4 = ECompiler.skipSpaces(this.s, 0);
            String var5 = this.s.substring(var4);
            if (var5.isEmpty()) {
               return null;
            } else if (!var5.startsWith("//") && !var5.startsWith(";")) {
               char var6 = var5.charAt(0);
               if (var6 == '@') {
                  String var39 = this.s.substring(var4 + 1);
                  if (ECompiler.this.labelmap.put(var39, var1) != null) {
                     this.err("Label redefinition", var4);
                  }

                  return null;
               } else {
                  Long var8 = null;
                  int var9 = 1;
                  if (var6 == '/' || Character.isDigit(var6)) {
                     if (var6 != '/') {
                        int var10 = ECompiler.scanHexInt(this.s, var4);
                        var8 = Long.parseLong(this.s.substring(var4, var10), 16);
                        var4 = var10;
                        var6 = this.s.charAt(var10);
                     }

                     if (var6 == '/') {
                        int var40 = ECompiler.scanHexInt(this.s, ++var4);
                        var9 = Integer.parseInt(this.s.substring(var4, var40), 16);
                        var4 = var40;
                        var6 = this.s.charAt(var40);
                     }

                     if (var6 != ':') {
                        this.err("Expected a semi-colon after explicit statement size", var4);
                     }

                     var4 = ECompiler.skipSpaces(this.s, var4 + 1);
                     var5 = this.s.substring(var4);
                  }

                  ECompiler.Parsed var7;
                  if (var5.equals("nop")) {
                     var7 = new ECompiler.Parsed(this.ectx.createNop());
                  } else if (var5.contains(" = ")) {
                     Boolean var41 = null;
                     int var11 = this.s.lastIndexOf("[BRANCH]");
                     if (var11 >= 0) {
                        var41 = false;
                     } else {
                        var11 = this.s.lastIndexOf("[SUB]");
                        if (var11 >= 0) {
                           var41 = true;
                        } else {
                           var11 = this.s.length();
                        }
                     }

                     ArrayList var12 = null;
                     int var13 = this.s.lastIndexOf("[BRANCH_HINTS:");
                     if (var13 >= 0) {
                        var41 = false;
                        var12 = new ArrayList();
                        int var14 = this.s.indexOf("]", var13);

                        for (String var18 : this.s.substring(var13 + 14, var14).split(",")) {
                           int var19 = Conversion.stringToInt(var18.trim(), -1);
                           if (var19 == -1) {
                              this.err("Illegal branch hint: " + var18, var4);
                           }

                           var12.add(new BranchTarget(new CodePointer(var19)));
                        }

                        if (var13 < var11) {
                           var11 = var13;
                        }
                     }

                     int var71 = this.s.indexOf(" = ");
                     ECompiler.Parsed var75 = ECompiler.this.new InternalCompiler(this, var4, var71).parse();
                     IEGeneric var81 = var75.result;
                     var75 = ECompiler.this.new InternalCompiler(this, var71 + 3, var11).parse();
                     IEGeneric var84 = var75.result;
                     if (var41 == null) {
                        var7 = new ECompiler.Parsed(this.ectx.createAssign(var81, var84));
                     } else {
                        var7 = new ECompiler.Parsed(this.ectx.createBranchAssign(var81, var84, var41));
                     }

                     if (var12 != null) {
                        if (!(var7.result instanceof IEAssign)) {
                           this.err("Expected a PC-assign since branch hints are provided!", var4);
                        }

                        ((IEAssign)var7.result).getBranchDetails(true).addCandidates(var12);
                     }
                  } else if (var5.startsWith("goto ")) {
                     var4 += 5;
                     String[] var42 = new String[1];
                     int[] var53 = new int[1];
                     int var61 = this.parseIROffset(this.s, var4, var42, var53);
                     ECompiler.Parsed var66 = new ECompiler.Parsed(this.ectx.createJump(var61));
                     var7 = var66;
                  } else if (var5.startsWith("jump ")) {
                     var4 += 5;
                     ECompiler.Parsed var43 = ECompiler.this.new InternalCompiler(this, var4).parse();
                     var7 = new ECompiler.Parsed(this.ectx.createJumpFar(var43.result));
                  } else if (var5.startsWith("if ")) {
                     var4 += 3;
                     int var44;
                     if ((var44 = this.s.indexOf(" goto ")) >= 0) {
                        ECompiler.Parsed var54 = ECompiler.this.new InternalCompiler(this, var4, var44).parse();
                        IEGeneric var62 = var54.result;
                        String[] var67 = new String[1];
                        int[] var72 = new int[1];
                        int var77 = this.parseIROffset(this.s, var44 + 6, var67, var72);
                        var54 = new ECompiler.Parsed(this.ectx.createJump(var77, var62));
                        var7 = var54;
                     } else {
                        if ((var44 = this.s.indexOf(" jump ")) < 0) {
                           throw new RuntimeException("Illegal type of if-statemet");
                        }

                        ECompiler.Parsed var56 = ECompiler.this.new InternalCompiler(this, var4, var44).parse();
                        IEGeneric var63 = var56.result;
                        var56 = ECompiler.this.new InternalCompiler(this, var44 + 6).parse();
                        IEGeneric var68 = var56.result;
                        var7 = new ECompiler.Parsed(this.ectx.createJumpFar(var68, var63));
                     }
                  } else if (var5.startsWith("switch ")) {
                     var4 += 7;
                     int var46 = ECompiler.scanUntil(this.s, var4, false, '(');
                     ECompiler.Parsed var58 = ECompiler.this.new InternalCompiler(this, var4, var46).parse();
                     IEGeneric var64 = var58.result;
                     String[] var69 = new String[1];
                     int[] var73 = new int[1];
                     int var78 = this.parseIROffset(this.s, var46 + 1, var69, var73);
                     var4 = var73[0];
                     var4 = ECompiler.skipSpaces(this.s, var4);
                     if (this.s.charAt(var4) != ')') {
                        throw new RuntimeException();
                     }

                     var4 = ECompiler.skipSpaces(this.s, ++var4);
                     if (this.s.charAt(var4) != ':') {
                        throw new RuntimeException();
                     }

                     var4++;
                     IESwitch var82 = this.ectx.createSwitch(var64, var78);

                     while (true) {
                        var4 = ECompiler.scanUntil(this.s, var4, false, '{');
                        if (var4 == this.s.length()) {
                           var7 = new ECompiler.Parsed(var82);
                           break;
                        }

                        var46 = ECompiler.scanUntil(this.s, ++var4, false, '}');
                        if (var46 == this.s.length()) {
                           throw new RuntimeException("Missing }");
                        }

                        int var85 = this.s.indexOf("->", var4);
                        if (var85 == -1 || var85 >= var46) {
                           throw new RuntimeException();
                        }

                        var58 = ECompiler.this.new InternalCompiler(this, var4, var85).parse();
                        IEGeneric var88 = var58.result;
                        int var90 = this.parseIROffset(this.s, var85 + 2, var69, var73);
                        var82.addCase(var88, var90);
                        var4 = var46 + 1;
                     }
                  } else if (var5.startsWith("call ")) {
                     var4 += 5;
                     int var48 = ECompiler.scanUntil(this.s, var4, false, '(');
                     ECompiler.Parsed var60 = ECompiler.this.new InternalCompiler(this, var4, var48).parse();
                     IEGeneric var65 = var60.result;
                     if (!this.s.startsWith("(", var48)) {
                        ECompiler.throwParsingException("Expected start of parameters for ECall", this.s, var48);
                     }

                     var4 = var48 + 1;
                     ArrayList var70 = new ArrayList();
                     boolean var74 = false;

                     while (!var74) {
                        var48 = ECompiler.scanUntil(this.s, var4, false, ',', ')');
                        if (this.s.charAt(var48) == ')') {
                           var74 = true;
                        }

                        if (var48 > var4 && !Strings.isBlank(this.s.substring(var4, var48))) {
                           IEGeneric var79 = ECompiler.this.new InternalCompiler(this, var4, var48).parse().result;
                           var70.add(var79);
                        }

                        var4 = var48 + 1;
                     }

                     if (!this.s.startsWith("->(", var4)) {
                        ECompiler.throwParsingException("Expected end of params and start of returns for ECall", this.s, var4);
                     }

                     var4 += 3;
                     ArrayList var80 = new ArrayList();
                     boolean var83 = false;

                     while (!var83) {
                        var48 = ECompiler.scanUntil(this.s, var4, false, ',', ')');
                        if (this.s.charAt(var48) == ')') {
                           var83 = true;
                        }

                        if (var48 > var4 && !Strings.isBlank(this.s.substring(var4, var48))) {
                           IEGeneric var86 = ECompiler.this.new InternalCompiler(this, var4, var48).parse().result;
                           var80.add(var86);
                        }

                        var4 = var48 + 1;
                     }

                     if (!this.s.startsWith("{", var4)) {
                        ECompiler.throwParsingException("Expected end of returns and start of return-location expression", this.s, var4);
                     }

                     var48 = ECompiler.scanUntil(this.s, ++var4, false, '}');
                     IEGeneric var87 = null;
                     if (var48 > var4 && !Strings.isBlank(this.s.substring(var4, var48))) {
                        var87 = ECompiler.this.new InternalCompiler(this, var4, var48).parse().result;
                     }

                     IWildcardTypeManager var89 = ECompiler.this.gctx.getWildcardTypeManager();
                     IWildcardPrototype var91 = var89.createPrototype(null, EUtil.getWildcardTypes(var89, var80), EUtil.getWildcardTypes(var89, var70), null);
                     IECall var20 = this.ectx.createCall(var65, var87, var80, var70, 0, null, var91);
                     var7 = new ECompiler.Parsed(var20);
                  } else {
                     if (!var5.startsWith("return")) {
                        if (var5.startsWith("untranslated")) {
                           throw new RuntimeException("TBI: untranslated");
                        }

                        throw new IllegalArgumentException("Unsupported statement type: " + var5);
                     }

                     var4 += 6;
                     var4 = ECompiler.skipSpaces(this.s, var4);
                     if (var4 == this.s.length()) {
                        var7 = new ECompiler.Parsed(this.ectx.createReturn());
                     } else {
                        ECompiler.Parsed var52 = ECompiler.this.new InternalCompiler(this, var4).parse();
                        var7 = new ECompiler.Parsed(this.ectx.createReturn(var52.result));
                     }
                  }

                  var7.requestedStatementSize = var9;
                  var7.requestedNativeAddress = var8;
                  return var7;
               }
            } else {
               throw new IllegalStateException("Unexpected line comment, it should have been trimmed");
            }
         }
      }

      private int parseIROffset(String var1, int var2, String[] var3, int[] var4) {
         var2 = ECompiler.skipSpaces(var1, var2);
         char var6 = var1.charAt(var2);
         Integer var5;
         int var7;
         if (var6 == '@') {
            var7 = ECompiler.scanToken(var1, var2 + 1);
            String var8 = var1.substring(var2 + 1, var7);
            var5 = (Integer)ECompiler.this.labelmap.get(var8);
            if (var5 == null) {
               var5 = (Integer)ECompiler.this.labelToCanary.get(var8);
               if (var5 == null) {
                  var5 = ECompiler.this.currentCanaryOffset++;
                  ECompiler.this.labelToCanary.put(var8, var5);
                  ECompiler.this.canaryToLabel.put(var5, var8);
               }
            }

            if (var3 != null) {
               var3[0] = var8;
            }
         } else {
            var7 = ECompiler.scanHexInt(var1, var2);
            var5 = Integer.parseInt(var1.substring(var2, var7), 16);
         }

         if (var4 != null) {
            var4[0] = var7;
         }

         return var5;
      }

      ECompiler.Parsed parse() {
         return this.parse(0);
      }

      private ECompiler.Parsed parse(int var1) {
         return this.parse(var1, 0, '\u0000', ECompiler.ExpressionType.ANY);
      }

      private ECompiler.Parsed parse(int var1, int var2, char var3, ECompiler.ExpressionType var4) {
         char var5 = 0;
         OperationType var6 = null;
         int[] var7 = new int[1];
         ArrayList var8 = new ArrayList();

         while (var1 < this.s.length()) {
            char var9 = this.s.charAt(var1++);
            if (!Character.isSpaceChar(var9)) {
               if (var9 == 'C') {
                  var9 = this.s.charAt(var1++);
                  if (var9 != '(') {
                     this.err("Expected opening paren right after C, got: " + var9, var1 - 1);
                  }

                  ECompiler.Parsed var19 = this.parse(var1, var2 + 1, ')', ECompiler.ExpressionType.COMPOSITION);
                  this.extractSliceAndType(var19);
                  var8.add(var19.result);
                  var1 = var19.pos;
               } else if (var9 == '(') {
                  ECompiler.Parsed var18 = this.parse(var1, var2 + 1, ')', ECompiler.ExpressionType.ANY);
                  this.extractSliceAndType(var18);
                  var8.add(var18.result);
                  var1 = var18.pos;
               } else {
                  if (var9 == ')' || var9 == ']' || var9 == '}' || var9 == '>' && var4 == ECompiler.ExpressionType.SEGVAR) {
                     var5 = var9;
                     break;
                  }

                  if (var9 != '?' && var9 != ':') {
                     if (ECompiler.trgOperators.contains(var9)) {
                        if (var6 != null) {
                           this.err("Operator already present", var1);
                        }

                        int var17 = ECompiler.scanUntilStopper(this.s, var1);
                        String var11 = this.s.substring(var1 - 1, var17);
                        if (var11.equals("=")) {
                           this.err("Nested assignments are not supported", var1);
                        }

                        var6 = this.parseOperator(var11, var7);
                        var1 = var17;
                     } else if (var9 == 'i' || var9 == 'f') {
                        ECompiler.Parsed var16 = this.parseImm(this.s, var1 - 1);
                        this.extractSliceAndType(var16);
                        var8.add(var16.result);
                        var1 = var16.pos;
                     } else if (var9 == 's' || var9 == 'v') {
                        ECompiler.Parsed var15 = this.parseVar(this.s, var1 - 1);
                        this.extractSliceAndType(var15);
                        var8.add(var15.result);
                        var1 = var15.pos;
                     } else if (var9 == 'm') {
                        ECompiler.Parsed var10 = this.parseMem(var1 - 1, var2);
                        this.extractSliceAndType(var10);
                        var8.add(var10.result);
                        var1 = var10.pos;
                     } else if (var9 == ',') {
                        if (var4 != ECompiler.ExpressionType.COMPOSITION) {
                           this.err("Unexpected comma outside composition", var1 - 1);
                        }
                     } else {
                        if (var9 == ';') {
                           throw new IllegalStateException("Unexpected comment, it should have been trimmed");
                        }

                        this.err("Character not parsed: " + var9, var1 - 1);
                     }
                  } else {
                     if (var6 != null) {
                        this.err("Conflicting operator use with ternary-operator expression", var1);
                     }

                     if (var9 == '?') {
                        var4 = ECompiler.ExpressionType.CONDITIONAL;
                     } else if (var4 != ECompiler.ExpressionType.CONDITIONAL) {
                        this.err("Partial termary-operator expression, colon was missing", var1);
                     }
                  }
               }
            }
         }

         if (var5 != var3) {
            this.err("Illegal closing parenthesis", var1 - 1);
         }

         ECompiler.Parsed var14 = new ECompiler.Parsed();
         var14.pos = var1;
         if (var6 == null) {
            if (var4 == ECompiler.ExpressionType.COMPOSITION) {
               if (var8.size() < 2) {
                  this.err("Composition needs at least two operands");
               }

               var14.result = this.ectx.createCompose(var8);
            } else if (var4 == ECompiler.ExpressionType.CONDITIONAL) {
               if (var8.size() != 3) {
                  this.err("Conditional needs exactly three operands");
               }

               var14.result = this.ectx.createCond((IEGeneric)var8.get(0), (IEGeneric)var8.get(1), (IEGeneric)var8.get(2));
            } else {
               if (var8.size() != 1) {
                  this.err("Expected a single item");
               }

               var14.result = (IEGeneric)var8.get(0);
            }
         } else {
            if (var8.isEmpty()) {
               this.err("Operation does not have any operand");
            }

            if (var8.size() == 1) {
               if (var6.isConversion()) {
                  var14.result = this.ectx.createConversionOperation(var6, (IEGeneric)var8.get(0), var7[0]);
               } else {
                  var14.result = this.ectx.createOperation(var6, (IEGeneric)var8.get(0));
               }
            } else if (var8.size() == 2) {
               var14.result = this.ectx.createOperation(var6, (IEGeneric)var8.get(0), (IEGeneric)var8.get(1));
            } else {
               this.err("Operation takes at most two operands");
            }
         }

         return var14;
      }

      private void extractSliceAndType(ECompiler.Parsed var1) {
         int var2 = var1.pos;

         while (var2 < this.s.length()) {
            if (this.s.charAt(var2) == '[') {
               ECompiler.Parsed var7 = this.parseRange(this.s, var2, var1.result);
               var2 = var7.pos;
               var1.result = ECompiler.this.gctx.createSlice(var1.result, (IERange)var7.result);
               var1.pos = var2;
            } else {
               if (!this.s.startsWith("<<", var2)) {
                  break;
               }

               var2 += 2;
               int var3 = this.s.indexOf(">>", var2);
               if (var3 < 0) {
                  this.err("Missing end of etype character sequence >>", var2);
               }

               String var4 = this.s.substring(var2, var3);
               var2 = var3 + 2;
               IWildcardType var5 = null;
               if (!var4.isEmpty()) {
                  var5 = ECompiler.this.gctx.getWildcardTypeManager().parse(var4, var1.result.getBitsize());
               }

               if (var1.result instanceof IEImm && !((IEImm)var1.result).isMutable()) {
                  var1.result = ((IEImm)var1.result).duplicateWithType(var5);
               } else if (!var1.result.setType(var5)) {
                  this.err(Strings.ff("Failed to set requested type '%s' on IR expression '%s'", var5, var1.result), var2);
               }

               var1.pos = var2;
            }
         }
      }

      private ECompiler.Parsed parseImm(String var1, int var2) {
         char var3 = var1.charAt(var2++);
         Assert.a(var3 == 'i' || var3 == 'f');
         int var4 = ECompiler.scanInt(var1, var2);
         int var5 = Integer.parseInt(var1.substring(var2, var4));
         Assert.a(var5 > 0);
         var2 = var4 + 1;
         char var6 = var1.charAt(var4);
         if (var6 != ':') {
            this.err("Expected semi-colon", var2);
         }

         if (var3 == 'f') {
            IEImm var20 = null;
            var4 = ECompiler.scanUntilStopper(var1, var2);
            String var21 = var1.substring(var2, var4);
            if (var5 == 32) {
               var20 = ECompiler.this.gctx.createImm(Float.parseFloat(var21));
            } else if (var5 == 64) {
               var20 = ECompiler.this.gctx.createImm(Double.parseDouble(var21));
            } else {
               this.err("Unsupported size for floating immediate: " + var5, var4);
            }

            return new ECompiler.Parsed(var20, var4);
         } else {
            BigInteger var7 = null;
            Long var8 = null;
            if (var1.charAt(var2) == '^') {
               int var23 = ++var2;
               var2++;
               if (var1.charAt(var23) != '+') {
                  this.err("Expected a '+' after '^'", var2);
               }

               var8 = this.getNativeAddress();
            } else if (var1.charAt(var2) == '$') {
               int var24 = ++var2;
               var2++;
               if (var1.charAt(var24) != '+') {
                  this.err("Expected a '+' after '$'", var2);
               }

               this.err("Unsupported: '$+x', use '^+x' for now", var2);
            } else if (var1.charAt(var2) == '\'') {
               int var25 = ++var2;
               var2++;
               var6 = var1.charAt(var25);
               if (var6 > 127) {
                  this.err("Char for EImm definition must be in [0, 0x7F) range", var2);
               }

               if (var1.charAt(var2++) != '\'') {
                  this.err("Expected a closing single-quote", var2);
               }

               var7 = BigInteger.valueOf(var6 & 255L);
            }

            if (var7 == null) {
               var4 = ECompiler.scanUntilStopperOrOperatorTrigger(var1, var2);
               String var9;
               byte var10;
               if (var1.charAt(var4 - 1) == 'h') {
                  var9 = var1.substring(var2, var4 - 1);
                  var10 = 16;
               } else {
                  var9 = var1.substring(var2, var4);
                  var10 = 10;
               }

               var7 = new BigInteger(var9, var10);
               var2 = var4;
            }

            if (var8 != null) {
               var7 = var7.add(BigInteger.valueOf(var8));
            }

            IEImm var22 = this.ectx.createImm(var7, var5);
            return new ECompiler.Parsed(var22, var2);
         }
      }

      private ECompiler.Parsed parseVar(String var1, int var2) {
         char var3 = var1.charAt(var2++);
         Assert.a(var3 == 's' || var3 == 'v');
         int var4 = ECompiler.scanInt(var1, var2);
         int var5 = Integer.parseInt(var1.substring(var2, var4));
         Assert.a(var5 > 0);
         var2 = var4 + 1;
         var3 = var1.charAt(var4);
         if (var3 != ':') {
            this.err("Expected semi-colon", var2);
         }

         var4 = ECompiler.scanUntilStopperOrOperatorTrigger(var1, var2);
         String var6 = var1.substring(var2, var4);
         Assert.a(var6.length() > 0);
         if (var6.startsWith("_")) {
            var6 = var6.substring(1);
         }

         IEVar var7 = ECompiler.this.gctx.getVariableByName(var6);
         if (var7 == null) {
            var7 = this.ectx.getVariableByName(var6);
            if (var7 == null) {
               if (var6.startsWith("par") || var6.startsWith("var")) {
                  int var24 = Integer.parseInt(var6.substring(3), 16);
                  if (var6.startsWith("var")) {
                     var24 = -var24;
                  }

                  var7 = this.ectx.getStackManager().createPureStackItem(var24, var5);
               } else if (var6.startsWith("ptr_par") || var6.startsWith("ptr_var")) {
                  int var23 = Integer.parseInt(var6.substring(7), 16);
                  if (var6.startsWith("ptr_var")) {
                     var23 = -var23;
                  }

                  var7 = this.ectx.createStackReference(var23, null);
               } else if (var6.startsWith("g")) {
                  long var8 = Long.parseLong(var6.substring(1), 16);
                  var7 = ECompiler.this.gctx.createGlobalVariable(var8, var5);
               } else if (var6.startsWith("ptr_g")) {
                  try {
                     long var20 = Long.parseLong(var6.substring(5), 16);
                     var7 = ECompiler.this.gctx.createGlobalReference(null, var20);
                  } catch (NumberFormatException var15) {
                     var7 = ECompiler.this.gctx.createGlobalReference(var6.substring(5), -1L);
                  }
               } else if (var6.startsWith("r")) {
                  var7 = ECompiler.this.gctx.createRegister(var6, var5);
               } else if (var6.startsWith("R")) {
                  var7 = ECompiler.this.gctx.createVirtualRegister(var6, var5);
               } else if (var6.startsWith("v")) {
                  var7 = this.ectx.createVar(var6, var5);
               } else if (var6.startsWith("$")) {
                  String var21 = var6.substring(1);
                  int var9 = var21.indexOf(36);
                  if (var9 >= 0) {
                     Integer.parseInt(var21.substring(var9 + 1));
                     var21 = var21.substring(0, var9);
                  }

                  var9 = var21.lastIndexOf(95);
                  if (var9 >= 0) {
                     String var26 = var21.substring(var9 + 1);
                     if (var26.startsWith("lo")) {
                        int var27 = Integer.parseInt(var21.substring(var9 + 3));
                        if (var27 != var5) {
                           this.err("Bitsize inconsistency", var4);
                        }

                        var21 = var21.substring(0, var9);
                        IEVar var12 = this.ectx.getVariableByName(var21);
                        Couple var13 = this.ectx.copyTruncatedVariable(var12, var5);
                        var7 = (IEVar)var13.getFirst();
                     } else if (var26.startsWith("hi")) {
                        this.err("TBI: vx_hiN", var4);
                     } else {
                        String var28 = var21.substring(0, var9);
                        String var29 = var21.substring(var9 + 1);
                        IEVar var30 = this.ectx.getVariableByName(var28);
                        IEVar var14 = this.ectx.getVariableByName(var29);
                        var7 = this.ectx.copyPairOfVariables(var30, var14);
                     }
                  } else {
                     IEVar var10 = this.ectx.getVariableByName(var21);
                     if (var10 == null) {
                        this.err("Variable " + var21 + " does not exist");
                     }

                     int var11 = 100;

                     while (var11-- > 0) {
                        var7 = this.ectx.copyVariable(var10);
                        if (var7.getName().equals(var6)) {
                           break;
                        }
                     }

                     if (var11 < 0) {
                        this.err("Too many copies were created, failed to create " + var6);
                     }
                  }

                  if (!var7.getName().equals(var6)) {
                     this.err("Illegal copy index at this point in compilation");
                  }
               } else {
                  this.err("Unsupported name prefix for EVar creation: " + var6, var4);
               }
            }
         }

         Assert.a(var7.getBitsize() == var5, "Illegal bitsize for variable " + var7 + ": " + var5);
         return new ECompiler.Parsed(var7, var4);
      }

      private ECompiler.Parsed parseMem(int var1, int var2) {
         char var3 = this.s.charAt(var1++);
         Assert.a(var3 == 'm');
         int var4 = ECompiler.scanInt(this.s, var1);
         int var5 = 0;

         try {
            var5 = Integer.parseInt(this.s.substring(var1, var4));
         } catch (NumberFormatException var9) {
            this.err("Illegal bitsize for memory", var1);
         }

         Assert.a(var5 > 0);
         var1 = var4 + 1;
         var3 = this.s.charAt(var4);
         IEGeneric var6 = null;
         if (var3 != '[') {
            if (var3 != '<') {
               this.err("Expected opening bracket for reference or segment", var1);
            }

            ECompiler.Parsed var7 = this.parse(var1, var2 + 1, '>', ECompiler.ExpressionType.SEGVAR);
            var6 = var7.result;
            if (!(var6 instanceof IEVar)) {
               this.err("The segment of an EMem must be an EVar", var1);
            }

            var1 = var7.pos;
            var3 = this.s.charAt(var1++);
            if (var3 != '[') {
               this.err("Expected opening bracket for reference", var1);
            }
         }

         ECompiler.Parsed var15 = this.parse(var1, var2 + 1, ']', ECompiler.ExpressionType.ANY);
         IEMem var8 = this.ectx.createMem(var6, var15.result, var5);
         return new ECompiler.Parsed(var8, var15.pos);
      }

      private ECompiler.Parsed parseRange(String var1, int var2, IEGeneric var3) {
         char var4 = var1.charAt(var2++);
         Assert.a(var4 == '[');
         int var5 = Strings.indexOf2(var1, var2, '[', ']');
         if (var5 < 0) {
            this.err("Closing range braket not found", var2);
         }

         boolean var6 = var1.charAt(var5) == ']';
         String var7 = var1.substring(var2, var5);
         var2 = var5 + 1;
         int var8 = 0;
         int var9 = 0;
         String[] var10 = var7.split(":", -1);
         if (var10.length == 1) {
            if (!var6) {
               this.err("Single bit syntax for range requires a ] closing bracket", var2);
            }

            var8 = Integer.parseInt(var10[0].trim());
            if (var8 < 0) {
               if (var3 == null) {
                  this.err("Range syntax with implicit end requires the sliced-expression", var2);
               }

               var8 += var3.getBitsize();
            }

            var9 = var8 + 1;
         } else if (var10.length == 2) {
            String var11 = var10[0].trim();
            String var12 = var10[1].trim();
            if (var11.isEmpty()) {
               var8 = 0;
            } else {
               var8 = Integer.parseInt(var11);
            }

            if (var12.isEmpty()) {
               if (var3 == null) {
                  this.err("Range syntax with implicit end requires the sliced-expression", var2);
               }

               var9 = var3.getBitsize();
            } else {
               var9 = Integer.parseInt(var12);
               if (var6) {
                  var9++;
               }
            }
         } else {
            this.err("Unexpected range", var2);
         }

         if (var8 < 0 || var9 < var8) {
            this.err("Illegal range", var2);
         }

         IERange var15 = ECompiler.this.gctx.createRange(var8, var9);
         return new ECompiler.Parsed(var15, var2);
      }

      private OperationType parseOperator(String var1, int[] var2) {
         var1 = var1.toLowerCase();
         switch (var1) {
            case "+":
               return OperationType.ADD;
            case "-":
               return OperationType.SUB;
            case "*":
               return OperationType.MUL;
            case "/":
               return OperationType.DIV_S;
            case "/u":
               return OperationType.DIV_U;
            case "%":
               return OperationType.REM_S;
            case "%u":
               return OperationType.REM_U;
            case "~":
               return OperationType.NOT;
            case "&":
               return OperationType.AND;
            case "|":
               return OperationType.OR;
            case "^":
               return OperationType.XOR;
            case "!":
               return OperationType.LOG_NOT;
            case "&&":
               return OperationType.LOG_AND;
            case "||":
               return OperationType.LOG_OR;
            case "==":
               return OperationType.LOG_EQ;
            case "!=":
               return OperationType.LOG_NEQ;
            case "<":
               return OperationType.LT_S;
            case "<=":
               return OperationType.LE_S;
            case ">":
               return OperationType.GT_S;
            case ">=":
               return OperationType.GE_S;
            case "<u":
               return OperationType.LT_U;
            case "<=u":
               return OperationType.LE_U;
            case ">u":
               return OperationType.GT_U;
            case ">=u":
               return OperationType.GE_U;
            case "<<":
               return OperationType.SHL;
            case ">>":
               return OperationType.SAR;
            case ">>>":
            case ">>u":
               return OperationType.SHR;
            case "<<>":
               return OperationType.ROL;
            case ">><":
               return OperationType.ROR;
            case "**":
               return OperationType.POW;
            case "#parity":
               return OperationType.PAR;
            case "#carry":
               return OperationType.CARRY;
            case "+f":
               return OperationType.FADD;
            case "-f":
               return OperationType.FSUB;
            case "*f":
               return OperationType.FMUL;
            case "/f":
               return OperationType.FDIV;
            case "==f":
               return OperationType.FEQ;
            case "!=f":
               return OperationType.FNE;
            case "<f":
               return OperationType.FLT;
            case ">f":
               return OperationType.FGT;
            case "<=f":
               return OperationType.FLE;
            case ">=f":
               return OperationType.FGE;
            default:
               if (var1.startsWith("#") && var1.contains("_")) {
                  int var5 = var1.indexOf(95);
                  String var6 = var1.substring(1, var5);
                  var2[0] = Integer.parseInt(var1.substring(var5 + 1));
                  return OperationType.fromName(var6);
               } else {
                  throw new RuntimeException("TBI: Operator: " + var1);
               }
         }
      }

      private void err(String var1) {
         this.err(var1, -1);
      }

      private void err(String var1, int var2) {
         ECompiler.InternalCompiler var3;
         for (var3 = this; var3.parent != null; var3 = var3.parent) {
            var2 += var3.parentBegin;
         }

         ECompiler.throwParsingException(var1, var3.s, var2);
      }
   }

   private static class Parsed {
      IEGeneric result;
      int pos;
      int requestedStatementSize;
      Long requestedNativeAddress;

      public Parsed() {
      }

      public Parsed(IEGeneric var1) {
         this.result = var1;
         this.pos = -1;
      }

      public Parsed(IEGeneric var1, int var2) {
         this.result = var1;
         this.pos = var2;
      }

      @Override
      public String toString() {
         return this.result == null ? "?" : this.result.toString();
      }
   }
}
