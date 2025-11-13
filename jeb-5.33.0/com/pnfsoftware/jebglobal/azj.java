package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.asm.type.CallingConventionName;
import com.pnfsoftware.jeb.core.units.code.asm.type.ICallingConvention;
import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.PrototypeAttribute;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.base.Couple;
import com.pnfsoftware.jeb.util.encoding.Conversion;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import java.text.ParseException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class azj {
   private static final ILogger E = GlobalLog.getLogger(azj.class);
   private static boolean sY = true;
   private static boolean ys = true;
   azn.RC pC;
   aza A;
   ayy kS;
   List wS;
   private List ld = new ArrayList();
   private boolean gp;
   private int oT;
   private Deque fI = new ArrayDeque();
   List UT = new ArrayList();

   public azj(azn.RC var1, aza var2, ayy var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide a parse tree");
      } else {
         this.pC = var1;
         if (var2 == null) {
            throw new IllegalArgumentException("Provide compilation options");
         } else {
            this.A = var2;
            if (var3 == null) {
               throw new IllegalArgumentException("Provide a type manager");
            } else {
               this.kS = var3;
               this.A();
            }
         }
      }
   }

   public boolean pC() {
      return this.wS != null;
   }

   void A() {
      this.pC(this.A.pC());
   }

   public int kS() {
      return this.oT;
   }

   public void pC(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Illegal packing alignment: " + var1);
      } else {
         this.oT = var1;
         Object[] var10000 = new Object[]{this.oT == Integer.MAX_VALUE ? "MAX" : Integer.toString(this.oT)};
      }
   }

   void pC(String var1, int var2) {
      this.fI.push(new Couple(var1, this.kS()));
      if (var2 > 0) {
         this.pC(var2);
      }
   }

   void A(String var1, int var2) {
      Couple var3;
      if (var1 != null) {
         boolean var4 = false;

         for (Couple var6 : this.fI) {
            if (var1.equals(var6.getFirst())) {
               var4 = true;
               break;
            }
         }

         if (!var4) {
            return;
         }

         Couple var7;
         do {
            var7 = (Couple)this.fI.pop();
         } while (!var1.equals(var7.getFirst()));

         var3 = var7;
      } else {
         var3 = (Couple)this.fI.pop();
      }

      if (var2 > 0) {
         this.pC(var2);
      } else {
         this.pC(((Integer)var3.getSecond()).intValue());
      }
   }

   public List wS() {
      if (this.pC()) {
         throw new IllegalStateException("Already converted");
      } else {
         this.wS = new ArrayList();
         Assert.a(this.ld.isEmpty());
         this.pC(this.pC);
         Assert.a(this.ld.isEmpty());
         Object[] var10000 = new Object[]{this.wS};
         return this.wS;
      }
   }

   private aye pC(String var1) {
      for (azh var3 : this.wS) {
         if (var3.pC != null && Strings.equals(var3.pC.getName(false), var1)) {
            return var3.pC;
         }
      }

      Object var4 = this.kS.pC().pC(var1);
      if (var4 == null) {
         var4 = this.kS.pC(var1, false);
         if (var4 == null) {
            E.warn("Cannot find type: %s", var1);
         }
      }

      return (aye)var4;
   }

   private azf UT() {
      if (this.ld.isEmpty()) {
         throw new IllegalStateException("Declaration stack is empty!");
      } else {
         return (azf)this.ld.get(this.ld.size() - 1);
      }
   }

   private azg E() {
      azf var1 = this.UT();
      if (var1.E.isEmpty()) {
         throw new IllegalStateException("Declarator stack of current declaration is empty!");
      } else {
         return (azg)var1.E.get(var1.E.size() - 1);
      }
   }

   private Map sY() {
      azf var1 = this.UT();
      return var1.E.isEmpty() ? var1.kS : ((azg)var1.E.get(var1.E.size() - 1)).A;
   }

   public static ParseTree pC(ParseTree var0, int var1, Class var2) {
      return var0.getChild(var1);
   }

   public static ParseTree pC(ParseTree var0, int var1) {
      return var0.getChild(var1);
   }

   void pC(azn.RC var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof azn.qg) {
         this.pC((azn.qg)var2);
      }
   }

   void pC(azn.qg var1) {
      ArrayList var2 = new ArrayList();

      Object var3;
      for (var3 = var1; var3.getChildCount() != 1; var3 = pC((ParseTree)var3, 0, azn.qg.class)) {
         var2.add(0, (azn.BP)pC((ParseTree)var3, 1, azn.BP.class));
      }

      var3 = pC((ParseTree)var3, 0, azn.BP.class);
      var2.add(0, (azn.BP)var3);
      int var4 = 0;

      for (azn.BP var6 : var2) {
         if (var4 >= 10000000) {
            Object[] var10000 = new Object[]{var4};
            break;
         }

         try {
            if (!this.pC(var6)) {
               continue;
            }
         } catch (Exception var10) {
            Token var8 = var6.getStart();
            String var9 = Strings.ff("Error: %d:%d: \"%s\"", var8.getLine(), var8.getCharPositionInLine() + 1, var8.getText());
            throw new RuntimeException(var9, var10);
         }

         var4++;
      }
   }

   boolean pC(azn.BP var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof azn.oP) {
         List var4 = this.pC((azn.oP)var2);
         if (var4 == null) {
            return false;
         } else {
            this.wS.addAll(var4);
            return true;
         }
      } else if (var2 instanceof azn.Pt) {
         List var3 = this.pC((azn.Pt)var2);
         this.wS.addAll(var3);
         return true;
      } else if (var2 instanceof azn.QM) {
         this.pC((azn.QM)var2);
         return true;
      } else {
         return false;
      }
   }

   List pC(azn.Pt var1) {
      int var2 = 0;
      ParseTree var3 = pC(var1, var2);
      if (!(var3 instanceof azn.Mh)) {
         return null;
      } else {
         azf var4 = new azf();
         this.ld.add(var4);
         this.pC((azn.Mh)var3);
         var2++;
         this.UT().E.add(new azg());
         azn.uX var5 = (azn.uX)pC(var1, var2, azn.uX.class);
         this.pC(var5);
         pC(var1, ++var2);
         this.ld.remove(var4);
         return this.pC(var4);
      }
   }

   void pC(azn.QM var1) {
      ParseTree var2 = var1.getChild(2);
      String var3 = var2.getText();
      ArrayList var4 = new ArrayList();
      if (var1.getChildCount() >= 6 && var1.getChild(3).getText().equals("(")) {
         azn.MG var5 = (azn.MG)pC(var1, 4, azn.MG.class);
         this.pC(var5, var4);
      }

      if (var3.equals("pack")) {
         if (var4.isEmpty()) {
            this.A();
         } else {
            String var9 = (String)var4.get(0);
            if (!var9.equals("show")) {
               if (Character.isDigit(var9.charAt(0))) {
                  int var6 = Conversion.stringToInt(var9);
                  this.pC(var6);
               } else {
                  String var10 = null;
                  int var7 = 0;
                  if (var4.size() >= 4) {
                     throw new RuntimeException("Unsupported argument count for pragma pack()");
                  }

                  if (var4.size() == 3) {
                     var10 = (String)var4.get(1);
                     var7 = Conversion.stringToInt((String)var4.get(2));
                  } else if (var4.size() == 2) {
                     String var8 = (String)var4.get(1);
                     if (Character.isDigit(var8.charAt(0))) {
                        var7 = Conversion.stringToInt(var8);
                     } else {
                        var10 = var8;
                     }
                  }

                  if (var9.equals("push")) {
                     this.pC(var10, var7);
                  } else {
                     if (!var9.equals("pop")) {
                        throw new RuntimeException("Unsupported pragma pack(): " + var9);
                     }

                     this.A(var10, var7);
                  }
               }
            }
         }
      } else {
         E.warn("Unsupported pragma, ignoring: \"%s\"", this.pC((ParseTree)var1));
      }
   }

   void pC(azn.MG var1, List var2) {
      ParseTree var3 = var1.getChild(0);
      if (var3 instanceof azn.lB) {
         var2.add(var3.getText());
      } else {
         this.pC((azn.MG)var3, var2);
         var2.add(var1.getChild(2).getText());
      }
   }

   List pC(azn.oP var1) {
      if (var1.getChildCount() == 1) {
         return null;
      } else {
         azf var2 = new azf();
         this.ld.add(var2);
         azn.Mh var3 = (azn.Mh)pC(var1, 0, azn.Mh.class);
         this.pC(var3);
         ParseTree var4 = var1.getChild(1);
         if (var4 instanceof azn.at) {
            this.pC((azn.at)var4);
         } else if (!var4.getText().equals(";")) {
            throw new IllegalStateException();
         }

         this.ld.remove(var2);
         return this.pC(var2);
      }
   }

   List pC(azf var1) {
      Object[] var10000 = new Object[]{var1};
      if (var1.E.size() == 0) {
         if (var1.UT.size() < 2 && (var1.UT.size() < 1 || var1.wS.size() < 1 && var1.A == null)) {
            azg var9 = new azg();
            var1.E.add(var9);
         } else {
            String var2 = (String)var1.UT.remove(var1.UT.size() - 1);
            azg var3 = new azg();
            var1.E.add(var3);
            var3.pC.add(var2);
         }

         var10000 = new Object[]{var1};
      } else if (var1.A == null && var1.wS.isEmpty() && var1.UT.isEmpty()) {
         Assert.a(var1.E.size() == 1);
         String var10 = (String)((azg)var1.E.get(0)).pC.remove(0);
         var1.UT.add(var10);
         var10000 = new Object[]{var1};
      }

      if (!var1.UT.isEmpty() && "operator".equals(var1.UT.get(var1.UT.size() - 1))) {
         String var11 = (String)var1.UT.remove(var1.UT.size() - 1);
         List var13 = ((azg)var1.E.get(0)).pC;
         if (!var13.isEmpty()) {
            var13.set(0, var11 + " " + (String)var13.get(0));
         }
      }

      var1.wS.addAll(var1.UT);
      var1.UT.clear();
      Object var12 = null;
      if (var1.A != null) {
         Assert.a(var1.wS.isEmpty());
         var12 = var1.A;
      } else {
         Assert.a(!var1.wS.isEmpty());
         String var14 = Strings.join(" ", var1.wS);
         Boolean var4 = null;
         if (var14.equals("int")) {
            var4 = true;
         } else if (var14.equals("unsigned int")) {
            var4 = false;
         }

         if (var4 != null) {
            Integer var5 = (Integer)var1.kS.get("modeBitSize");
            if (var5 != null) {
               var12 = this.kS.pC().pC(var5 / 8, var4);
            }
         }

         if (var12 == null) {
            var12 = this.kS.E(var14);
         }
      }

      var10000 = new Object[]{var12};
      ArrayList var15 = new ArrayList();

      for (azg var17 : var1.E) {
         Object var6 = var12;

         while (!var17.kS.isEmpty()) {
            Object var7 = (aye)var17.kS.remove(0);
            if (var7 instanceof ayt) {
               Assert.a(((ayt)var7).E() == null);
               var7 = this.kS.pC((INativeType)var6, ((ayt)var7).getReferenceCount());
            } else if (var7 instanceof ayg) {
               Assert.a(((ayg)var7).E() == null);
               var7 = this.kS.A((INativeType)var6, ((ayg)var7).getElementCount());
            } else {
               if (!(var7 instanceof ays var8)) {
                  throw new RuntimeException();
               }

               var8.pC((aye)var6, false);
               this.pC(var8, var17.A);
            }

            var6 = var7;
         }

         if (var6 instanceof ays var18) {
            this.pC(var18, var1.kS);
         }

         String var19;
         if (var17.pC.isEmpty()) {
            var19 = "__ANONYMOUS__";
         } else {
            var19 = (String)var17.pC.get(0);
         }

         int var20 = 0;
         if (var17.wS > 0) {
            var20 = var17.wS;
         }

         if (var1.pC) {
            var6 = this.kS.pC(var19, (INativeType)var6);
            var19 = null;
         }

         if (var6 instanceof ays) {
            ((ays)var6).pC(var19);
         }

         var15.add(new azh((aye)var6, var19, var20, this.A(var1)));
      }

      return var15;
   }

   private void pC(ays var1, Map var2) {
      for (String var4 : var2.keySet()) {
         if (var4 != null) {
            if (CallingConventionName.find(var4) != null) {
               ICallingConvention var5 = this.kS.A().getConvention(CallingConventionName.find(var4).toString());
               if (var5 == null) {
                  throw new RuntimeException("Unsupported or unknown calling convention: " + var4);
               }

               var1.pC(var5);
            } else if (var4.equals("noreturn")) {
               var1.pC(PrototypeAttribute.NoReturn, false);
            } else if (var4.equals("nothrow")) {
               var1.pC(PrototypeAttribute.NoThrow, false);
            } else if (var4.equals("leaf")) {
               var1.pC(PrototypeAttribute.Leaf, false);
            } else if (var4.equals("pure")) {
               var1.pC(PrototypeAttribute.Pure, false);
            } else if (var4.equals("const")) {
               var1.pC(PrototypeAttribute.Const, false);
            } else if (var4.equals("volatile")) {
               var1.pC(PrototypeAttribute.Volatile, false);
            } else if (!Strings.isContainedIn(var4, "extern", "static", "inline", "__inline", "__inline__", "forceinline", "__forceinline", "__forceinline__")) {
               E.debug("Unsupported specifier in prototype: %s", var4);
            }
         }
      }
   }

   void pC(azn.at var1) {
      if (var1.getChildCount() == 1) {
         this.pC((azn.jY)pC(var1, 0, azn.jY.class));
      } else {
         if (var1.getChildCount() != 3) {
            throw new RuntimeException();
         }

         this.pC((azn.at)pC(var1, 0, azn.at.class));
         this.pC((azn.jY)pC(var1, 2, azn.jY.class));
      }
   }

   void pC(azn.jY var1) {
      if (var1.getChildCount() == 1) {
         this.UT().E.add(new azg());
         this.pC((azn.uX)pC(var1, 0, azn.uX.class));
      } else {
         this.UT().E.add(new azg());
         this.pC((azn.uX)pC(var1, 0, azn.uX.class));
      }
   }

   void pC(azn.Mh var1) {
      for (ParseTree var3 : var1.children) {
         this.pC((azn.p)var3);
      }
   }

   void pC(azn.p var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof azn.SM) {
         this.pC((azn.SM)var2);
      } else if (var2 instanceof azn.Go) {
         this.pC((azn.Go)var2);
      } else if (var2 instanceof azn.BW) {
         this.pC((azn.BW)var2);
      } else {
         if (!(var2 instanceof azn.lX)) {
            throw new aze(var2);
         }

         this.pC((azn.lX)var2);
      }
   }

   void pC(azn.SM var1) {
      if (var1.getChildCount() != 1) {
         throw new IllegalStateException();
      } else {
         ParseTree var2 = var1.getChild(0);
         if (var2.getChildCount() != 0) {
            throw new IllegalStateException();
         } else {
            String var3 = var2.getText();
            if (var3.equals("typedef")) {
               this.UT().pC = true;
            } else {
               if (!Strings.isContainedIn(var3, "extern", "static", "auto", "register")) {
                  throw new aze(var2);
               }

               this.sY().put(var3, null);
            }
         }
      }
   }

   void pC(azn.Go var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof TerminalNode) {
         String var3 = var2.getText();
         this.UT().wS.add(var3);
      } else if (var2 instanceof azn.Gn) {
         this.UT().A = this.pC((azn.Gn)var2);
      } else if (var2 instanceof azn.gc) {
         this.pC((azn.gc)var2);
      } else if (var2 instanceof azn.Sb) {
         this.UT().A = this.pC((azn.Sb)var2);
      } else {
         if (!(var2 instanceof azn.rQ)) {
            throw new aze(var2);
         }

         String var4 = this.pC((azn.Pc)pC(var2, 2, azn.Pc.class));
         this.UT().wS.add(var4);
      }
   }

   void pC(azn.gc var1) {
      String var2 = var1.getChild(0).getText();
      this.UT().UT.add(var2);
   }

   void pC(azn.AF var1) {
      if (var1.getChildCount() == 1) {
         this.pC((azn.BW)pC(var1, 0, azn.BW.class));
      } else {
         this.pC((azn.AF)pC(var1, 0, azn.AF.class));
         this.pC((azn.BW)pC(var1, 1, azn.BW.class));
      }
   }

   void pC(azn.BW var1) {
      String var2 = var1.getText();
      if (!var2.equals("__extension__")) {
         this.sY().put(var2, null);
      }
   }

   void pC(azn.lX var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2.getChildCount() == 0) {
         String var3 = var2.getText();
         this.sY().put(var3, null);
      } else if (var2 instanceof azn.Tb) {
         this.pC((azn.Tb)var2);
      } else {
         if (!(var2 instanceof azn.hi)) {
            throw new IllegalStateException();
         }

         this.pC((azn.hi)var2);
      }
   }

   void pC(azn.hi var1) {
      azn.be var2 = (azn.be)pC(var1, 3, azn.be.class);
      this.pC(var2);
   }

   void pC(azn.be var1) {
      for (byte var2 = 0; var2 < var1.getChildCount(); var2 += 2) {
         this.pC((azn.pB)pC(var1, var2, azn.pB.class));
      }
   }

   void pC(azn.pB var1) {
      String var2 = this.pC((ParseTree)var1);
      if (var2.startsWith("__mode__")) {
         String var3 = var2.substring(8).trim();
         if (var3.startsWith("(") && var3.endsWith(")")) {
            var3 = var3.substring(1, var3.length() - 1).trim();
            Integer var4 = null;
            switch (var3) {
               case "__word__":
                  var4 = this.kS.getSlotSize() * 8;
                  break;
               case "__QI__":
                  var4 = 8;
                  break;
               case "__HI__":
                  var4 = 16;
                  break;
               case "__SI__":
                  var4 = 32;
                  break;
               case "__DI__":
                  var4 = 64;
                  break;
               case "__TI__":
                  var4 = 128;
            }

            if (var4 != null) {
               this.sY().put("modeBitSize", var4);
            }
         }
      }

      if (var2.startsWith("__") && var2.endsWith("__")) {
         var2 = var2.substring(2, var2.length() - 2);
      }

      if (var2.equals("noreturn")) {
         this.sY().put("noreturn", true);
      } else if (var2.equals("nothrow")) {
         this.sY().put("nothrow", true);
      } else if (var2.equals("leaf")) {
         this.sY().put("leaf", true);
      } else if (var2.equals("pure")) {
         this.sY().put("pure", true);
      } else if (var2.equals("const")) {
         this.sY().put("const", true);
      } else if (var2.startsWith("aligned")) {
         String var8 = var2.substring(7).trim();
         var8 = Strings.ltrim(var8, '(');
         var8 = Strings.rtrim(var8, ')');
         var8 = var8.trim();
         int var12 = Conversion.stringToInt(var8);
         this.sY().put("align", var12);
      } else {
         Object[] var10000 = new Object[]{var2};
      }
   }

   void pC(azn.uX var1) {
      int var2 = 0;

      ParseTree var3;
      for (var3 = var1.getChild(var2); var3 instanceof azn.LK; var3 = var1.getChild(++var2)) {
         this.pC((azn.LK)var3);
      }

      if (var3 instanceof azn.rm) {
         this.pC((azn.rm)var3);
         var3 = var1.getChild(++var2);
      }

      while (var3 instanceof azn.LK) {
         this.pC((azn.LK)var3);
         var3 = var1.getChild(++var2);
      }

      this.pC((azn.eW)var3);
      var2++;

      while (var2 < var1.getChildCount()) {
         var3 = var1.getChild(var2);
         this.pC((azn.Rk)var3);
         var2++;
      }
   }

   void pC(azn.LK var1) {
      ParseTree var2 = var1.getChild(0);
      if (!(var2 instanceof TerminalNode)) {
         this.pC((azn.lX)pC(var1, 0, azn.lX.class));
      }
   }

   void pC(azn.Rk var1) {
      if (var1.getChildCount() == 1) {
         this.pC((azn.hi)pC(var1, 0, azn.hi.class));
      }
   }

   void pC(azn.rm var1) {
      ParseTree var2 = var1.getChild(0);
      String var3 = var2.getText();
      if (!var3.equals("*") && !var3.equals("&")) {
         throw new aze(var1);
      } else {
         this.E().kS.add(this.kS.pC(null, 1));
         if (var1.getChildCount() != 1) {
            ParseTree var4 = var1.getChild(1);
            if (var4 instanceof azn.AF) {
               this.pC((azn.AF)var4);
               if (var1.getChildCount() != 2) {
                  azn.rm var5 = (azn.rm)pC(var1, 2, azn.rm.class);
                  this.pC(var5);
               }
            } else {
               this.pC((azn.rm)var4);
            }
         }
      }
   }

   void pC(azn.eW var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof TerminalNode) {
         String var8 = var2.getText();
         if (var8.equals("(")) {
            if (!var1.getChild(2).getText().equals(")")) {
               throw new IllegalStateException();
            }

            azn.uX var10 = (azn.uX)pC(var1, 1, azn.uX.class);
            this.pC(var10);
         } else {
            this.E().pC.add(var8);
         }
      } else if (!(var2 instanceof azn.eW)) {
         throw new aze(var1);
      } else {
         ParseTree var3 = var1.getChild(1);
         if (var3 instanceof TerminalNode && var3.getText().equals("[")) {
            int var11 = 2;
            ParseTree var6 = var1.getChild(var11);
            if (var6 instanceof azn.AF) {
               var11++;
            }

            var6 = var1.getChild(var11);
            int var9;
            if (var6 instanceof TerminalNode && var6.getText().equals("]")) {
               var9 = 0;
            } else {
               if (!(var6 instanceof azn.cq)) {
                  throw new RuntimeException("Limited subset of array declarators are handled (2)");
               }

               if (!var1.getChild(var11 + 1).getText().equals("]")) {
                  throw new RuntimeException("Limited subset of array declarators are handled");
               }

               Object var7 = this.pC((azn.cq)var6);
               if (var7 == null || !(var7 instanceof Integer)) {
                  throw new RuntimeException("Cannot evaluate array size");
               }

               var9 = (Integer)var7;
            }

            if (var9 < 0) {
               throw new RuntimeException("Cannot evaluate array size");
            }

            Object var13;
            if (!this.gp) {
               var13 = this.kS.A(null, var9);
            } else {
               var13 = this.kS.pC(null, 1);
            }

            this.E().kS.add(var13);
            this.pC((azn.eW)var2);
         } else {
            if (!var3.getText().equals("(")) {
               throw new aze(var1);
            }

            ays var4 = this.kS.sY();
            this.E().kS.add(var4);
            ParseTree var5 = var1.getChild(2);
            if (var5.getChildCount() != 0 || !var5.getText().equals(")")) {
               if (!var1.getChild(3).getText().equals(")")) {
                  throw new RuntimeException("Limited subset of function declarators are handled");
               }

               this.pC((azn.hR)var5, var4);
            }

            this.pC((azn.eW)var2);
         }
      }
   }

   void pC(azn.hR var1, ays var2) {
      if (var1.getChildCount() != 1) {
         if (!var1.getChild(2).getText().equals("...")) {
            throw new IllegalStateException();
         }

         var2.pC(PrototypeAttribute.VarArg, false);
      }

      azn.wY var3 = (azn.wY)pC(var1, 0, azn.wY.class);
      this.pC(var3, var2);
   }

   void pC(azn.wY var1, ays var2) {
      if (var1.getChildCount() == 1) {
         this.pC((azn.Bv)pC(var1, 0, azn.Bv.class), var2);
      } else {
         this.pC((azn.wY)pC(var1, 0, azn.wY.class), var2);
         this.pC((azn.Bv)pC(var1, 2, azn.Bv.class), var2);
      }
   }

   void pC(azn.Bv var1, ays var2) {
      azf var3 = new azf();
      this.ld.add(var3);
      this.gp = true;
      ParseTree var4 = var1.getChild(0);
      if (var4 instanceof azn.Mh) {
         this.pC((azn.Mh)var4);
         var3.E.add(new azg());
         this.pC((azn.uX)pC(var1, 1, azn.uX.class));
      } else {
         if (!(var4 instanceof azn.GK)) {
            throw new IllegalStateException();
         }

         this.pC((azn.GK)var4);
         if (var1.getChildCount() >= 2) {
            var3.E.add(new azg());
            this.pC((azn.Av)pC(var1, 1, azn.Av.class));
         }
      }

      this.gp = false;
      this.ld.remove(var3);
      List var5 = this.pC(var3);
      if (var5.size() != 1 || !TypeUtil.isVoid(((azh)var5.get(0)).pC)) {
         int var6 = 1 + var2.getCountOfParameters();

         for (azh var8 : var5) {
            aye var9 = var8.pC;
            String var10 = var8.A;
            if (var10 == null || var10.equals("__ANONYMOUS__")) {
               var10 = "param" + var6;
            }

            var2.pC(var9, var10, false);
            var6++;
         }
      }
   }

   void pC(azn.GK var1) {
      for (ParseTree var3 : var1.children) {
         this.pC((azn.p)var3);
      }
   }

   void pC(azn.Av var1) {
      ParseTree var2 = var1.getChild(0);
      if (var1.getChildCount() == 1 && var2 instanceof azn.rm) {
         this.pC((azn.rm)var2);
      } else {
         if (!(var2 instanceof azn.rm)) {
            if (var2 instanceof azn.m) {
               this.pC((azn.m)var2);
               if (var1.getChildCount() != 1) {
                  throw new aze(var1);
               }

               return;
            }

            throw new aze(var1);
         }

         this.pC((azn.rm)var2);
         azn.m var3 = (azn.m)pC(var1, 1, azn.m.class);
         this.pC(var3);
      }
   }

   void pC(azn.m var1) {
      throw new aze(var1);
   }

   aym pC(azn.Sb var1) {
      ParseTree var2 = var1.getChild(0);
      Assert.a(var2.getText().equals("enum"));
      if (var1.getChildCount() == 2) {
         String var12 = var1.getChild(1).getText();
         aye var14 = this.kS.UT(var12);
         aym var15 = (aym)TypeUtil.getNonAlias(var14, aym.class);
         if (var15 != null) {
            return var15;
         } else if (!this.A.A()) {
            throw new IllegalStateException("Creation of new complex type is denied: " + var12);
         } else {
            var15 = this.kS.pC(var12);
            this.UT.add(var15);
            return var15;
         }
      } else {
         String var3 = null;
         int var4 = 1;
         ParseTree var5 = var1.getChild(var4);
         if (var5 instanceof azn.Tb) {
            this.pC((azn.Tb)var5);
            var5 = var1.getChild(++var4);
         }

         if (!(var5 instanceof TerminalNode)) {
            throw new IllegalStateException();
         } else {
            if (!var5.getText().equals("{")) {
               var3 = var5.getText();
               var5 = var1.getChild(++var4);
            }

            if (var5 instanceof azn.jM) {
               E.debug("C++11 enum-base is ignored in: \"%s\"", this.pC((ParseTree)var1));
               var5 = var1.getChild(++var4);
            }

            if (!var5.getText().equals("{")) {
               throw new IllegalStateException();
            } else {
               var4++;
               boolean var6 = false;
               if (var3 == null) {
                  var3 = "__synth_enum_" + this.kS.getTypes().size();
                  var6 = true;
               }

               azn.B var7 = (azn.B)pC(var1, var4, azn.B.class);
               int var9 = 0;
               String var10 = var3;

               while (true) {
                  aye var11 = this.kS.UT(var3);
                  aym var8 = (aym)TypeUtil.getNonAlias(var11, aym.class);
                  if (var8 == null || var8.getConstants().isEmpty()) {
                     if (var9 >= 1) {
                        Object[] var10000 = new Object[]{var10, var3};
                     }

                     if (var8 == null) {
                        if (!this.A.A()) {
                           throw new IllegalStateException("Creation of new complex type is denied: " + var3);
                        }

                        var8 = this.kS.pC(var3);
                        if (var6) {
                           var8.A(2097152, false);
                        }
                     }

                     this.pC(var7, var8);
                     Object[] var17 = new Object[]{var8, var8.getConstants().size()};
                     return var8;
                  }

                  if (!sY) {
                     throw new RuntimeException("Enum defined already");
                  }

                  var3 = var10 + "_copy" + ++var9;
               }
            }
         }
      }
   }

   void pC(azn.B var1, aym var2) {
      ParseTree var3 = var1.getChild(0);
      if (var3 instanceof azn.Sf) {
         this.pC((azn.Sf)var3, var2);
      } else {
         this.pC((azn.B)var3, var2);
         this.pC((azn.Sf)pC(var1, 2, azn.Sf.class), var2);
      }
   }

   void pC(azn.Sf var1, aym var2) {
      azn.Hv var3 = (azn.Hv)pC(var1, 0, azn.Hv.class);
      String var4 = var3.getText();
      if (var1.getChildCount() >= 3) {
         ParseTree var5 = var1.getChild(2);
         String var6 = var5.getText();
         ayl var8 = var2.A(var6);
         int var7;
         if (var8 != null) {
            var7 = var8.getValue();
         } else {
            var7 = Conversion.stringToInt(var6);
         }

         var2.pC(var4, var7);
      } else {
         var2.pC(var4);
      }
   }

   ayv pC(azn.Gn var1) {
      ParseTree var2 = var1.getChild(0);
      boolean var3 = var2.getText().equals("union");
      if (var1.getChildCount() == 2) {
         String var12 = var1.getChild(1).getText();
         aye var14 = this.kS.UT(var12);
         ayv var16 = (ayv)TypeUtil.getNonAlias(var14, ayv.class);
         if (var16 != null) {
            return var16;
         } else if (!this.A.A()) {
            throw new IllegalStateException("Creation of new complex type is denied: " + var12);
         } else {
            var16 = this.kS.pC(var12, var3 ? 0 : this.kS(), this.A(this.UT()));
            this.UT.add(var16);
            return var16;
         }
      } else {
         String var4 = null;
         int var5 = 1;
         ParseTree var6 = var1.getChild(var5);
         if (var6 instanceof azn.ma) {
            this.pC((azn.ma)var6);
            var6 = var1.getChild(++var5);
         }

         if (!(var6 instanceof TerminalNode)) {
            throw new IllegalStateException();
         } else {
            if (!var6.getText().equals("{")) {
               var4 = var6.getText();
               var6 = var1.getChild(++var5);
            }

            if (!var6.getText().equals("{")) {
               throw new IllegalStateException();
            } else {
               var5++;
               boolean var7 = false;
               if (var4 == null) {
                  var4 = "__synth_struct_" + this.kS.getTypes().size();
                  var7 = true;
               }

               int var9 = 0;
               String var10 = var4;

               while (true) {
                  aye var11 = this.kS.UT(var4);
                  ayv var8 = (ayv)TypeUtil.getNonAlias(var11, ayv.class);
                  if (var8 == null || var8.getFields().isEmpty()) {
                     if (var9 >= 1) {
                        Object[] var10000 = new Object[]{var10, var4};
                     }

                     if (var8 == null) {
                        if (!this.A.A()) {
                           throw new IllegalStateException("Creation of new complex type is denied: " + var4);
                        }

                        var8 = this.kS.pC(var4, var3 ? 0 : this.kS(), this.A(this.UT()));
                        if (var7) {
                           var8.A(2097152, false);
                        }
                     }

                     var6 = var1.getChild(var5);
                     if (var6 instanceof azn.Dr var18) {
                        this.pC(var18, var8);
                     } else if (!var6.getText().equals("}")) {
                        throw new RuntimeException();
                     }

                     Object[] var19 = new Object[]{var8, var8.getFields().size()};
                     return var8;
                  }

                  if (!ys) {
                     throw new RuntimeException("Struct/union defined already");
                  }

                  var4 = var10 + "_copy" + ++var9;
               }
            }
         }
      }
   }

   private int A(azf var1) {
      Object var2 = var1.kS.get("align");
      return var2 instanceof Integer ? (Integer)var2 : 0;
   }

   void pC(azn.ma var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof azn.Tb) {
         this.pC((azn.Tb)var2);
      } else {
         this.pC((azn.ma)var2);
         this.pC((azn.Tb)pC(var1, 1, azn.Tb.class));
      }
   }

   void pC(azn.Tb var1) {
      azn.io var2 = (azn.io)pC(var1, 2, azn.io.class);
      this.pC(var2);
   }

   void pC(azn.io var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof azn.Pj) {
         this.pC((azn.Pj)var2);
      } else {
         this.pC((azn.io)var2);
         this.pC((azn.Pj)pC(var1, 1, azn.Pj.class));
      }
   }

   void pC(azn.Pj var1) {
      azn.gJ var2 = (azn.gJ)pC(var1, 0, azn.gJ.class);
      String var3 = var2.getText();
      if (var3.equals("align")) {
         int var4 = Conversion.stringToInt(var1.getChild(2).getText());
         Object[] var10000 = new Object[]{var4};
         this.sY().put(var3, var4);
      } else if (var3.equals("noreturn")) {
         Object[] var5 = new Object[0];
         this.sY().put(var3, true);
      } else {
         Object[] var6 = new Object[]{var3};
      }
   }

   void pC(azn.Dr var1, ayv var2) {
      if (var1.getChildCount() == 1) {
         this.pC((azn.ia)pC(var1, 0, azn.ia.class), var2);
      } else {
         if (var1.getChildCount() != 2) {
            throw new RuntimeException();
         }

         this.pC((azn.Dr)pC(var1, 0, azn.Dr.class), var2);
         this.pC((azn.ia)pC(var1, 1, azn.ia.class), var2);
      }
   }

   void pC(azn.ia var1, ayv var2) {
      if (var1.getChildCount() == 1) {
         ParseTree var11 = var1.getChild(0);
         if (var11 instanceof azn.QM) {
            this.pC((azn.QM)pC(var1, 0, azn.QM.class));
         } else if (!var11.toString().equals(";")) {
            throw new RuntimeException("TBI: assert() statement in structure");
         }
      } else {
         azf var3 = new azf();
         this.ld.add(var3);
         azn.eS var4 = (azn.eS)pC(var1, 0, azn.eS.class);
         this.pC(var4);
         int var5 = 1;
         ParseTree var6 = var1.getChild(var5);
         if (var6 instanceof azn.dF) {
            this.pC((azn.dF)var6);
            var5++;
         }

         while (var5 < var1.getChildCount()) {
            var6 = var1.getChild(var5);
            if (!(var6 instanceof azn.Rk)) {
               break;
            }

            var5++;
         }

         if (var5 < var1.getChildCount() && var1.getChild(var5).toString().equals(";")) {
            this.ld.remove(var3);

            for (azh var9 : this.pC(var3)) {
               int var10 = 0;
               if ("__ANONYMOUS__".equals(var9.A)) {
                  var10 |= 2097152;
               }

               var2.pC(var9.A, var9.pC, -1, var9.kS, var9.wS, var10);
            }
         } else {
            throw new RuntimeException();
         }
      }
   }

   void pC(azn.eS var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof azn.Go) {
         this.pC((azn.Go)var2);
      } else if (var2 instanceof azn.BW) {
         this.pC((azn.BW)var2);
      }

      if (var1.getChildCount() == 2) {
         this.pC((azn.eS)pC(var1, 1, azn.eS.class));
      }
   }

   void pC(azn.dF var1) {
      if (var1.getChildCount() == 1) {
         this.pC((azn.Zn)pC(var1, 0, azn.Zn.class));
      } else {
         if (var1.getChildCount() != 3) {
            throw new RuntimeException();
         }

         this.pC((azn.dF)pC(var1, 0, azn.dF.class));
         this.pC((azn.Zn)pC(var1, 2, azn.Zn.class));
      }
   }

   void pC(azn.Zn var1) {
      this.UT().E.add(new azg());
      int var2 = var1.getChildCount();
      if (var2 == 1 || var2 == 3) {
         azn.uX var3 = (azn.uX)pC(var1, 0, azn.uX.class);
         this.pC(var3);
      }

      if (var2 >= 2) {
         byte var7 = 2;
         if (var2 == 2) {
            this.E().pC.add("_");
            var7 = 1;
         }

         azn.qt var4 = (azn.qt)pC(var1, var7, azn.qt.class);
         Object var5 = this.pC(var4);
         if (!(var5 instanceof Integer)) {
            throw new RuntimeException();
         }

         int var6 = (Integer)var5;
         if (var6 <= 0) {
            throw new RuntimeException();
         }

         this.E().wS = var6;
      }
   }

   boolean pC(Object var1) {
      if (var1 instanceof Boolean) {
         return (Boolean)var1;
      } else {
         throw new RuntimeException("TBI: evalAsBool for object: " + var1);
      }
   }

   Object pC(azn.cq var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.HE)pC(var1, 0, azn.HE.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object pC(azn.qt var1) {
      azn.HE var2 = (azn.HE)pC(var1, 0, azn.HE.class);
      return this.pC(var2);
   }

   Object pC(azn.HE var1) {
      azn.wq var2 = (azn.wq)pC(var1, 0, azn.wq.class);
      Object var3 = this.pC(var2);
      if (var1.getChildCount() >= 2) {
         if (this.pC(var3)) {
            var3 = this.pC((azn.Yd)pC(var1, 2, azn.Yd.class));
         } else {
            var3 = this.pC((azn.HE)pC(var1, 4, azn.HE.class));
         }
      }

      return var3;
   }

   Object pC(azn.wq var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.aB)pC(var1, 0, azn.aB.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object pC(azn.aB var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.vJ)pC(var1, 0, azn.vJ.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object pC(azn.vJ var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.dE)pC(var1, 0, azn.dE.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object pC(azn.dE var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.Ws)pC(var1, 0, azn.Ws.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object pC(azn.Ws var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.co)pC(var1, 0, azn.co.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object pC(azn.co var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.Ee)pC(var1, 0, azn.Ee.class));
      } else {
         Object var2 = this.pC((azn.co)pC(var1, 0, azn.co.class));
         Object var3 = this.pC((azn.Ee)pC(var1, 2, azn.Ee.class));
         String var4 = var1.getChild(1).toString();
         boolean var5 = true;
         if ("!=".equals(var4)) {
            var5 = false;
         } else if (!"==".equals(var4)) {
            throw new RuntimeException();
         }

         return var2 != null && var2.equals(var3) == var5;
      }
   }

   Object pC(azn.Ee var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.wn)pC(var1, 0, azn.wn.class));
      } else {
         Object var2 = this.pC((azn.Ee)pC(var1, 0, azn.Ee.class));
         Object var3 = this.pC((azn.wn)pC(var1, 2, azn.wn.class));
         if (var2 instanceof Comparable && var3 instanceof Comparable) {
            int var4 = ((Comparable)var2).compareTo(var3);
            String var5 = var1.getChild(1).toString();
            switch (var5) {
               case "<":
                  return var4 < 0;
               case ">":
                  return var4 > 0;
               case "<=":
                  return var4 <= 0;
               case ">=":
                  return var4 >= 0;
            }
         }

         throw new RuntimeException();
      }
   }

   Object pC(azn.wn var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.Sv)pC(var1, 0, azn.Sv.class));
      } else {
         String var2 = var1.getChild(1).getText();
         azc.Av var3 = var2.equals("<<") ? azc.Av.E : azc.Av.sY;
         Object var4 = this.pC((azn.wn)pC(var1, 0, azn.wn.class));
         Object var5 = this.pC((azn.Sv)pC(var1, 2, azn.Sv.class));
         return azc.pC(var3, var4, var5);
      }
   }

   Object pC(azn.Sv var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.Pe)pC(var1, 0, azn.Pe.class));
      } else {
         String var2 = var1.getChild(1).getText();
         azc.Av var3 = var2.equals("+") ? azc.Av.pC : azc.Av.A;
         Object var4 = this.pC((azn.Sv)pC(var1, 0, azn.Sv.class));
         Object var5 = this.pC((azn.Pe)pC(var1, 2, azn.Pe.class));
         return azc.pC(var3, var4, var5);
      }
   }

   Object pC(azn.Pe var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.yt)pC(var1, 0, azn.yt.class));
      } else {
         String var2 = var1.getChild(1).getText();
         azc.Av var3 = var2.equals("*") ? azc.Av.kS : (var2.equals("/") ? azc.Av.wS : azc.Av.UT);
         Object var4 = this.pC((azn.Pe)pC(var1, 0, azn.Pe.class));
         Object var5 = this.pC((azn.yt)pC(var1, 2, azn.yt.class));
         return azc.pC(var3, var4, var5);
      }
   }

   Object pC(azn.yt var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.Vo)pC(var1, 0, azn.Vo.class));
      } else {
         azn.yt var2 = (azn.yt)pC(var1, var1.getChildCount() - 1, azn.yt.class);
         return this.pC(var2);
      }
   }

   Object pC(azn.Vo var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.vh)pC(var1, 0, azn.vh.class));
      } else {
         if (var1.getChildCount() >= 2) {
            String var2 = var1.getChild(0).getText();
            if (var2.equals("sizeof") || var2.equals("__alignof__")) {
               aye var3 = null;
               if (var1.getChildCount() == 2) {
                  String var4 = var1.getChild(1).getText();
                  if (var4.startsWith("(") && var4.endsWith(")")) {
                     String var5 = var4.substring(1, var4.length() - 1).trim();
                     if (var5.startsWith("\"") && var5.endsWith("\"")) {
                        String var6 = var5.substring(1, var5.length() - 1);

                        try {
                           return Formatter.unescapeString(var6).length();
                        } catch (ParseException var8) {
                           throw new RuntimeException(var8);
                        }
                     }

                     var3 = this.pC(var5);
                  }
               } else if (var1.getChildCount() == 4) {
                  String var9 = this.pC((azn.Pc)pC(var1, 2, azn.Pc.class));
                  var3 = this.pC(var9);
               }

               if (var3 != null) {
                  return var3.getSize();
               }
            }
         }

         throw new RuntimeException("eval() error: " + var1.getText());
      }
   }

   Object pC(azn.vh var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.lB)pC(var1, 0, azn.lB.class));
      } else {
         throw new RuntimeException("eval() error: " + var1.getText());
      }
   }

   Object pC(azn.lB var1) {
      if (var1.getChildCount() == 1) {
         String var2 = var1.getChild(0).getText();
         if (var2.endsWith("U")) {
            var2 = var2.substring(0, var2.length() - 1);
         }

         int var3 = Conversion.stringToInt(var2, -1);
         if (var3 != -1) {
            return var3;
         }

         Iterator var4 = this.kS.ys();

         while (var4.hasNext()) {
            ayl var5 = ((aym)var4.next()).A(var2);
            if (var5 != null) {
               return var5.getValue();
            }
         }
      } else if (var1.getChildCount() == 3 && var1.getChild(1) instanceof azn.Yd) {
         return this.pC((azn.Yd)pC(var1, 1, azn.Yd.class));
      }

      throw new RuntimeException("eval() error: " + var1.getText());
   }

   Object pC(azn.Yd var1) {
      if (var1.getChildCount() == 1) {
         return this.pC((azn.cq)pC(var1, 0, azn.cq.class));
      } else {
         throw new RuntimeException("eval() error: " + var1.getText());
      }
   }

   String pC(azn.Pc var1) {
      String var2 = this.pC((ParseTree)var1);
      if (var2.startsWith("struct")) {
         var2 = var2.substring(6);
      } else if (var2.startsWith("union")) {
         var2 = var2.substring(5);
      }

      return var2.trim();
   }

   private String pC(ParseTree var1) {
      StringBuilder var2 = new StringBuilder();
      this.pC(var1, var2);
      return var2.toString().trim();
   }

   private void pC(ParseTree var1, StringBuilder var2) {
      if (var1 instanceof TerminalNode) {
         var2.append(var1);
         var2.append(' ');
      } else if (var1 instanceof RuleNode) {
         int var3 = var1.getChildCount();

         for (int var4 = 0; var4 < var3; var4++) {
            ParseTree var5 = var1.getChild(var4);
            this.pC(var5, var2);
         }
      }
   }
}
