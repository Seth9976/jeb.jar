package com.pnfsoftware.jeb.core.units.code.asm.decompiler.ir.compiler;

import com.pnfsoftware.jeb.client.Licensing;
import com.pnfsoftware.jeb.client.S;
import com.pnfsoftware.jeb.util.collect.Sets;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.StructuredLogger;
import com.pnfsoftware.jebglobal.aco;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class EPatternCompiler {
   private static final StructuredLogger logger = aco.pC(EPatternCompiler.class);
   public static final int FLAG_NO_VARIABLE_LEAF_OVERLAP = 1;
   public static final int FLAG_SAME_BITSIZE_FOR_LEAVES = 2;
   public static final int FLAG_INVALIDATE_DFA = 256;
   private static final int FMASK_INPUT = 3;
   private static final int FMASK_OUTPUT = 256;
   public static final EPatternCompiler DEFAULT_COMPILER = new EPatternCompiler();
   private int cflags;
   private static Set trgLeaves = Sets.createNonNulls('@', 'T', '$', 'V', '#', 'N');
   private static Set trgOperators = Sets.createNonNulls('+', '-', '*', '/', '%', '&', '|', '^', '~', '<', '>', '=', '!', '.', '_', '[', '?', ':');

   public EPatternCompiler(int var1) {
      this.cflags = var1;
   }

   public EPatternCompiler() {
      this(0);
   }

   public int getFlags() {
      return this.cflags;
   }

   public EPatternCompiler.EPattern compile(EPatternCompiler.EPattern var1) {
      if (var1.compiled) {
         throw new IllegalStateException("Pattern is already compiled");
      } else if (var1.inputs.isEmpty()) {
         throw new IllegalArgumentException("A pattern needs at least one input");
      } else {
         for (EPatternCompiler.EPattern.P var3 : var1.inputs) {
            var3.pflags = var3.pflags | this.cflags & 3;

            try {
               this.compile1(var3);
            } catch (Exception var6) {
               logger.catching(var6, "Error compiling input pattern");
               throw var6;
            }
         }

         EPatternCompiler.EPattern.P var7 = var1.output;
         if (var7 != null) {
            var7.pflags = var7.pflags | this.cflags & 256;

            try {
               this.compile1(var7);
            } catch (Exception var5) {
               logger.catching(var5, "Error compiling ouput pattern");
               throw var5;
            }
         }

         var1.compiled = true;
         return var1;
      }
   }

   private void compile1(EPatternCompiler.EPattern.P var1) {
      int var2 = 0;
      int var3 = -1;
      int var4 = 0;
      int var5 = 0;
      int var6 = 0;
      int var7 = 0;

      for (String var11 : var1.pstrings) {
         byte var12 = 0;
         boolean var13 = false;
         int var14 = 0;

         while (var14 < var11.length()) {
            char var15 = Character.toUpperCase(var11.charAt(var14));
            if (!EPatternCompiler.EPattern.specialChars.contains(var15)) {
               break;
            }

            if (var15 == '>') {
               var3 = var2;
               var14++;
            } else if (var15 == 'X') {
               var13 = true;
               var14++;
            } else {
               throwParsingException("Unsupported special char", var11, var14);
            }
         }

         if (var13) {
            if (var5 == 0) {
               var4++;
            }
         } else {
            var5++;
         }

         EPatternCompiler.InternalCompiler var20 = new EPatternCompiler.InternalCompiler(var11);
         EPatternCompiler.Parsed var16 = var20.parse(var14);
         EPatternCompiler.Parsed var17 = new EPatternCompiler.Parsed();
         if (var16.continuationRequested) {
            var17 = var20.parse(var16.pos);
         }

         int var18 = var16.score + var17.score;
         if (var18 > var7) {
            var6 = var2;
            var7 = var18;
         }

         EPatternCompiler.EPattern.P.E var19 = new EPatternCompiler.EPattern.P.E(var12 | var1.pflags, var16.result, var17.result);
         var1.clist.add(var19);
         var2++;
      }

      if (var3 < 0) {
         var3 = var6;
      }

      var1.itrigger = var3;
      var1.ireplbegin = var4;
      var1.ireplend = var4 + var5;
   }

   private static void throwParsingException(String var0, String var1, int var2) {
      var0 = Strings.ff("Illegal input pattern: %s", var0);
      if (var2 >= 0) {
         var0 = var0 + Strings.ff(":\n  %s\n  %s^", var1, Strings.generate(' ', var2));
      }

      throw new IllegalStateException(var0);
   }

   public static class EPattern {
      static final char CH_TRIGGER = '>';
      static final char CH_KEEP = 'X';
      private static final Set specialChars = Sets.createNonNulls('>', 'X');
      String name;
      boolean compiled;
      List inputs = new ArrayList();
      EPatternCompiler.EPattern.P output;
      IEMatchVerifier verifier;
      IEPatternReplacer replacer;
      Boolean pureexp;
      Boolean pureexpout;

      public static EPatternCompiler.EPattern cc(String... var0) {
         return cc(0, var0);
      }

      public static EPatternCompiler.EPattern cc(int var0, String... var1) {
         return create().addInput(var0, var1).compile();
      }

      public static EPatternCompiler.EPattern create() {
         return create(null);
      }

      public static EPatternCompiler.EPattern create(String var0) {
         return new EPatternCompiler.EPattern(var0);
      }

      private EPattern(String var1) {
         this.name = var1;
      }

      public String getName() {
         return this.name;
      }

      public EPatternCompiler.EPattern setInput(String var1) {
         return this.setInput(0, var1);
      }

      public EPatternCompiler.EPattern setInput(int var1, String var2) {
         if (!this.inputs.isEmpty()) {
            EPatternCompiler.logger.warn(S.L("An IR input pattern is being overwritten! Was this intended?"));
            if (Licensing.isDebugBuild()) {
               throw new RuntimeException("IR pattern already set!");
            }
         }

         this.inputs.clear();
         return this.addInput(0, var2);
      }

      public EPatternCompiler.EPattern addInput(String... var1) {
         return this.addInput(0, var1);
      }

      public EPatternCompiler.EPattern addInput(int var1, String... var2) {
         this.inputs.add(new EPatternCompiler.EPattern.P(var1, var2));
         return this;
      }

      public boolean isPureInputExpression() {
         return this.pureexp;
      }

      public boolean isPureOuputExpression() {
         return this.pureexpout;
      }

      public EPatternCompiler.EPattern.P getInput(int var1) {
         return (EPatternCompiler.EPattern.P)this.inputs.get(var1);
      }

      public List getInputs() {
         return this.inputs;
      }

      public EPatternCompiler.EPattern setOutput(String... var1) {
         return this.setOutput(0, var1);
      }

      public EPatternCompiler.EPattern setOutput(int var1, String... var2) {
         this.output = new EPatternCompiler.EPattern.P(var1, var2);
         return this;
      }

      public boolean hasOutput() {
         return this.output != null;
      }

      public EPatternCompiler.EPattern.P getOutput() {
         return this.output;
      }

      public EPatternCompiler.EPattern setVerifier(IEMatchVerifier var1) {
         this.verifier = var1;
         return this;
      }

      public IEMatchVerifier getVerifier() {
         return this.verifier;
      }

      public EPatternCompiler.EPattern setCustomReplacer(IEPatternReplacer var1) {
         this.replacer = var1;
         return this;
      }

      public IEPatternReplacer getCustomReplacer() {
         return this.replacer;
      }

      private Boolean determinePureInputExpression() {
         if (this.pureexp != null) {
            throw new IllegalStateException();
         } else {
            for (EPatternCompiler.EPattern.P var2 : this.inputs) {
               boolean var3 = var2.clist.size() == 1 && ((EPatternCompiler.EPattern.P.E)var2.clist.get(0)).rhs == null;
               if (this.pureexp == null) {
                  this.pureexp = var3;
               } else if (this.pureexp != var3) {
                  return null;
               }
            }

            this.pureexpout = this.output != null && this.output.clist.size() == 1 && ((EPatternCompiler.EPattern.P.E)this.output.clist.get(0)).rhs == null;
            return this.pureexp;
         }
      }

      public EPatternCompiler.EPattern compile(EPatternCompiler var1) {
         if (var1 == null) {
            var1 = EPatternCompiler.DEFAULT_COMPILER;
         }

         EPatternCompiler.EPattern var2 = var1.compile(this);
         if (this.determinePureInputExpression() == null) {
            throw new RuntimeException("Inconsistent inputs!");
         } else {
            return var2;
         }
      }

      public EPatternCompiler.EPattern compile() {
         return this.compile(null);
      }

      public EPatternCompiler.EPattern compile(int var1) {
         return this.compile(new EPatternCompiler(var1));
      }

      public boolean isCompiled() {
         return this.compiled;
      }

      @Override
      public String toString() {
         if (!this.isCompiled()) {
            return "(Not compiled)";
         } else {
            StringBuilder var1 = new StringBuilder();
            if (this.name != null) {
               var1.append("Pattern: ").append(this.name).append("\n");
            }

            int var2 = 0;

            for (EPatternCompiler.EPattern.P var4 : this.inputs) {
               if (this.inputs.size() == 1 && this.output != null) {
                  var1.append("Input:\n");
               } else if (this.inputs.size() > 1) {
                  Strings.ff(var1, "Input #%d:\n", var2 + 1);
               }

               var1.append(var4).append("\n");
               var2++;
            }

            if (this.output != null) {
               Strings.ff(var1, "Output:\n%s\n", this.output);
            }

            if (var1.length() > 0) {
               int var5 = var1.length() - 1;
               if (var1.charAt(var5) == '\n') {
                  var1.deleteCharAt(var5);
               }
            }

            return var1.toString();
         }
      }

      public static class P {
         private int pflags;
         private String[] pstrings;
         int itrigger;
         int ireplbegin;
         int ireplend;
         List clist = new ArrayList();

         P(int var1, String... var2) {
            this.pflags = var1;
            this.pstrings = var2;
         }

         public int getFlags() {
            return this.pflags;
         }

         public List getLines() {
            return this.clist;
         }

         public EPatternCompiler.EPattern.P.E getLine(int var1) {
            return (EPatternCompiler.EPattern.P.E)this.clist.get(var1);
         }

         public int getCountOfLines() {
            return this.clist.size();
         }

         public EPatternCompiler.EPattern.P.E getTriggerLine() {
            return (EPatternCompiler.EPattern.P.E)this.clist.get(this.itrigger);
         }

         public int getTriggerIndex() {
            return this.itrigger;
         }

         public int getFirstReplacedLineIndex() {
            return this.ireplbegin;
         }

         public int getLastReplacedLineIndex() {
            return this.ireplend - 1;
         }

         public int getCountOfReplacedLines() {
            return this.ireplend - this.ireplbegin;
         }

         @Override
         public String toString() {
            if (this.clist.isEmpty()) {
               return "(Pattern is not compiled)";
            } else {
               StringBuilder var1 = new StringBuilder();
               int var2 = 0;

               for (EPatternCompiler.EPattern.P.E var4 : this.clist) {
                  if (var2 >= 1) {
                     var1.append("\n");
                  }

                  if (this.clist.size() >= 2) {
                     StringBuilder var5 = new StringBuilder();
                     if (this.itrigger == var2) {
                        var5.append('>');
                     }

                     if (var2 < this.ireplbegin || var2 >= this.ireplend) {
                        var5.append('X');
                     }

                     var5.append(Strings.generate(' ', 3 - var5.length()));
                     var1.append((CharSequence)var5);
                  }

                  var1.append(var4);
                  var2++;
               }

               return var1.toString();
            }
         }

         static class E {
            int eflags;
            INode expr;
            INode rhs;

            E(int var1, INode var2) {
               this.eflags = var1;
               this.expr = var2;
               this.rhs = null;
            }

            E(int var1, INode var2, INode var3) {
               this.eflags = var1;
               this.expr = var2;
               this.rhs = var3;
            }

            public int getFlag() {
               return this.eflags;
            }

            public INode getExpressionOrLhs() {
               return this.expr;
            }

            public INode getOptionalRhs() {
               return this.rhs;
            }

            @Override
            public String toString() {
               StringBuilder var1 = new StringBuilder();
               var1.append(this.expr);
               if (this.rhs != null) {
                  var1.append(" = ").append(this.rhs);
               }

               return var1.toString();
            }
         }
      }
   }

   private static class InternalCompiler {
      private final String s;
      private int score;

      InternalCompiler(String var1) {
         this.s = var1;
      }

      EPatternCompiler.Parsed parse() {
         return this.parse(0);
      }

      EPatternCompiler.Parsed parse(int var1) {
         EPatternCompiler.Parsed var2 = this.parse(var1, 0, '\u0000');
         var2.score = this.score;
         return var2;
      }

      private EPatternCompiler.Parsed parse(int var1, int var2, char var3) {
         char var4 = 0;
         O var5 = null;
         ArrayList var6 = new ArrayList();
         boolean var7 = false;

         while (var1 < this.s.length()) {
            char var8 = this.s.charAt(var1++);
            if (!Character.isSpaceChar(var8)) {
               if (var8 == '(') {
                  EPatternCompiler.Parsed var15 = this.parse(var1, var2 + 1, ')');
                  this.sliceResult(var15);
                  var6.add(var15.result);
                  var1 = var15.pos;
               } else {
                  if (var8 == ')' || var8 == ']' || var8 == '}') {
                     var4 = var8;
                     break;
                  }

                  int var9 = 0;
                  if (var5 == null && var6.isEmpty() && var1 < this.s.length() && (var8 == '-' || var8 == '+') && Character.isDigit(this.s.charAt(var1))) {
                     var9 = var8 == '-' ? -1 : 1;
                     var8 = this.s.charAt(var1++);
                  }

                  if (var9 == 0 && EPatternCompiler.trgOperators.contains(var8)) {
                     if (var5 == O.COND) {
                        if (var8 != ':') {
                           this.err("Illegal ternary operator", var1);
                        }
                     } else if (var5 != null) {
                        switch (var5) {
                           case ADD:
                           case MUL:
                           case AND:
                           case OR:
                           case XOR:
                              break;
                           default:
                              this.err("Operator already present: " + var5, var1);
                        }
                     }

                     int var17 = this.scanUntil(this.s, var1);
                     String var11 = this.s.substring(var1 - 1, var17);
                     if (var11.equals("=")) {
                        if (var2 != 0) {
                           this.err("Illegal assignment", var1 - 1);
                        }

                        var7 = true;
                        break;
                     }

                     O var12 = this.parseOperator(var11, var1);
                     if (var5 != null) {
                        if (var5 == O.ADD && var12 != O.ADD) {
                           this.err("Illegal additive sequence", var1);
                        }

                        if (var5 == O.MUL && var12 != O.MUL) {
                           this.err("Illegal multiplicative sequence", var1);
                        }

                        if (var5 == O.AND && var12 != O.AND) {
                           this.err("Illegal bitwise-and sequence", var1);
                        }

                        if (var5 == O.OR && var12 != O.OR) {
                           this.err("Illegal bitwise-or sequence", var1);
                        }

                        if (var5 == O.XOR && var12 != O.XOR) {
                           this.err("Illegal bitwise-xor sequence", var1);
                        }
                     }

                     var5 = var12;
                     var1 = var17;
                  } else if (Character.isDigit(var8)) {
                     EPatternCompiler.Parsed var10 = this.parseImm(var1 - 1, var9 < 0 ? -1 : 1);
                     this.sliceResult(var10);
                     var6.add(var10.result);
                     var1 = var10.pos;
                     this.score++;
                  } else {
                     var8 = Character.toUpperCase(var8);
                     if (EPatternCompiler.trgLeaves.contains(var8)) {
                        EPatternCompiler.Parsed var16 = this.parseLeaf(var1 - 1);
                        this.sliceResult(var16);
                        var6.add(var16.result);
                        var1 = var16.pos;
                        this.score += 2;
                     } else {
                        this.err("Character not parsed: " + var8, var1 - 1);
                     }
                  }
               }
            }
         }

         if (var4 != var3) {
            this.err("Illegal closing parenthesis", var1 - 1);
         }

         EPatternCompiler.Parsed var14 = new EPatternCompiler.Parsed();
         var14.pos = var1;
         var14.continuationRequested = var7;
         if (!var7 && var5 != null) {
            if (var6.isEmpty()) {
               this.err("Missing operand(s)");
            }

            if (var5 == O.SUB && var6.size() == 1) {
               var5 = O.NEG;
            }

            var14.result = new Node(var5, (INode[])var6.toArray(new INode[var6.size()]));
         } else {
            if (var6.size() != 1) {
               this.err("Missing operand");
            }

            var14.result = (INode)var6.get(0);
         }

         return var14;
      }

      private void sliceResult(EPatternCompiler.Parsed var1) {
         int var2 = var1.pos;
         if (var2 < this.s.length() && this.s.charAt(var2) == '[') {
            if (this.s.startsWith("[H1]", var2)) {
               var1.result = new Node(O.SLICE_HALF1, var1.result);
               var1.pos += 4;
            } else if (this.s.startsWith("[H2]", var2)) {
               var1.result = new Node(O.SLICE_HALF2, var1.result);
               var1.pos += 4;
            }
         }
      }

      int scanUntil(String var1, int var2) {
         while (var2 < var1.length()) {
            char var3 = var1.charAt(var2);
            if (var3 != '(' && var3 != ')' && !Character.isSpaceChar((int)var3) && var3 != '[' && var3 != ']' && var3 != '{' && var3 != '}') {
               var2++;
               continue;
            }
            break;
         }

         return var2;
      }

      int scanInt(String var1, int var2) {
         while (var2 < var1.length()) {
            char var3 = var1.charAt(var2);
            if (Character.isDigit((int)var3)) {
               var2++;
               continue;
            }
            break;
         }

         return var2;
      }

      EPatternCompiler.Parsed parseImm(int var1, int var2) {
         int var3 = this.scanUntil(this.s, var1);
         String var4 = this.s.substring(var1, var3);
         long var5 = Conversion.stringToLong(var4, -1L);
         if (var5 < 0L) {
            this.err("Illegal immediate: " + var4, var1 - 1);
         }

         var1 = var3;
         int var7 = -1;
         if (var3 < this.s.length() && this.s.charAt(var3) == '{') {
            var1 = var3 + 1;
            var3 = this.scanUntil(this.s, var1);
            if (var3 >= this.s.length() || this.s.charAt(var3) != '}') {
               this.err("", var3);
            }

            var7 = Conversion.stringToInt(this.s.substring(var1, var3), -1);
            var1 = var3 + 1;
         }

         return new EPatternCompiler.Parsed(new Leaf(var2 * var5, 0, var7), var1);
      }

      EPatternCompiler.Parsed parseLeaf(int var1) {
         char var2 = this.s.charAt(var1++);
         int var3 = this.scanUntil(this.s, var1);
         if (var3 == var1) {
            this.err("Missing leaf identifier", var1);
         }

         byte var4;
         if (var2 == '$') {
            var4 = 15;
         } else if (var2 == '@') {
            if (!this.s.substring(var1, var3).startsWith("LASTBIT")) {
               throw new RuntimeException("Unknown @ marker for leaf");
            }

            var4 = 32;
            var1 += 7;
         } else if (var2 == 'T') {
            var4 = 7;
         } else if (var2 == 'N') {
            var4 = 8;
         } else if (var2 == 'V') {
            var4 = 2;
         } else {
            if (var2 != '#') {
               throw new RuntimeException("Unknown leaf identifier: " + var2);
            }

            var4 = 1;
         }

         int var5 = 0;
         char var6 = this.s.charAt(var1);
         if (Character.isDigit(var6)) {
            var3 = this.scanInt(this.s, var1);
            var5 = Integer.parseInt(this.s.substring(var1, var3));
            var1 = var3;
         } else {
            this.err("Missing leaf id", var1);
         }

         return new EPatternCompiler.Parsed(new Leaf(var5, 0, var4), var1);
      }

      O parseOperator(String var1, int var2) {
         String var3 = var1.toLowerCase();
         switch (var3) {
            case "+":
               return O.ADD;
            case "-":
               return O.SUB;
            case "*":
               return O.MUL;
            case "/":
               return O.DIV_S;
            case "%":
               return O.REM_S;
            case "/u":
               return O.DIV_U;
            case "%u":
               return O.REM_U;
            case "~":
               return O.NOT;
            case "&":
               return O.AND;
            case "|":
               return O.OR;
            case "^":
               return O.XOR;
            case "!":
               return O.LNOT;
            case "&&":
               return O.LAND;
            case "||":
               return O.LOR;
            case "==":
               return O.EQ;
            case "!=":
               return O.NE;
            case "<":
               return O.LT_S;
            case "<=":
               return O.LE_S;
            case ">":
               return O.GT_S;
            case ">=":
               return O.GE_S;
            case "<u":
               return O.LT_U;
            case "<=u":
               return O.LE_U;
            case ">u":
               return O.GT_U;
            case ">=u":
               return O.GE_U;
            case "<<":
               return O.SHL;
            case ">>":
               return O.SAR;
            case ">>>":
            case ">>u":
               return O.SHR;
            case "...":
               return O.COMPOSE_2;
            case ".=.":
               return O.COMPOSE_2EQ;
            case "_carry":
               return O.CARRY;
            case "_ncadd":
               return O.NCADD;
            case "_ncsub":
               return O.NCSUB;
            case "_lsb":
               return O.SLICE_FIRSTBIT;
            case "_msb":
               return O.SLICE_LASTBIT;
            case "_hsb":
               return O.SLICE_HALFBIT;
            case "_parity":
               return O.PARITY;
            case "_trn":
               return O.TRN;
            case "_trn8":
               return O.TRN8;
            case "_trn16":
               return O.TRN16;
            case "_trn32":
               return O.TRN32;
            case "_trn64":
               return O.TRN64;
            case "_trn128":
               return O.TRN128;
            case "_ext":
               return O.EXT;
            case "_ext8":
               return O.EXT8;
            case "_ext16":
               return O.EXT16;
            case "_ext32":
               return O.EXT32;
            case "_ext64":
               return O.EXT64;
            case "_ext128":
               return O.EXT128;
            case "?":
            case ":":
               return O.COND;
            default:
               throw new RuntimeException("TBI: Operator: " + var1);
         }
      }

      void err(String var1) {
         this.err(var1, -1);
      }

      void err(String var1, int var2) {
         EPatternCompiler.throwParsingException(var1, this.s, var2);
      }
   }

   private static class Parsed {
      INode result;
      int pos;
      int score;
      boolean continuationRequested;

      public Parsed() {
      }

      public Parsed(INode var1) {
         this.result = var1;
         this.pos = -1;
      }

      public Parsed(INode var1, int var2) {
         this.result = var1;
         this.pos = var2;
      }

      @Override
      public String toString() {
         return this.result == null ? "?" : this.result.toString();
      }
   }
}
