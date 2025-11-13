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

public class bcj {
   private static final ILogger gO = GlobalLog.getLogger(bcj.class);
   private static final int nf = 10000000;
   public static final String q = "__ANONYMOUS__";
   private static boolean gP = true;
   private static boolean za = true;
   bcp.PY RF;
   bca xK;
   bby Dw;
   List Uv;
   private List lm = new ArrayList();
   private boolean zz;
   private int JY;
   private Deque HF = new ArrayDeque();
   List oW = new ArrayList();

   public bcj(bcp.PY var1, bca var2, bby var3) {
      if (var1 == null) {
         throw new IllegalArgumentException("Provide a parse tree");
      } else {
         this.RF = var1;
         if (var2 == null) {
            throw new IllegalArgumentException("Provide compilation options");
         } else {
            this.xK = var2;
            if (var3 == null) {
               throw new IllegalArgumentException("Provide a type manager");
            } else {
               this.Dw = var3;
               this.RF();
            }
         }
      }
   }

   public boolean q() {
      return this.Uv != null;
   }

   void RF() {
      this.q(this.xK.q());
   }

   public int xK() {
      return this.JY;
   }

   public void q(int var1) {
      if (var1 <= 0) {
         throw new IllegalArgumentException("Illegal packing alignment: " + var1);
      } else {
         this.JY = var1;
         Object[] var10000 = new Object[]{this.JY == Integer.MAX_VALUE ? "MAX" : Integer.toString(this.JY)};
      }
   }

   void q(String var1, int var2) {
      this.HF.push(new Couple(var1, this.xK()));
      if (var2 > 0) {
         this.q(var2);
      }
   }

   void RF(String var1, int var2) {
      Couple var3;
      if (var1 != null) {
         boolean var4 = false;

         for (Couple var6 : this.HF) {
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
            var7 = (Couple)this.HF.pop();
         } while (!var1.equals(var7.getFirst()));

         var3 = var7;
      } else {
         var3 = (Couple)this.HF.pop();
      }

      if (var2 > 0) {
         this.q(var2);
      } else {
         this.q(((Integer)var3.getSecond()).intValue());
      }
   }

   public List Dw() {
      if (this.q()) {
         throw new IllegalStateException("Already converted");
      } else {
         this.Uv = new ArrayList();
         Assert.a(this.lm.isEmpty());
         this.q(this.RF);
         Assert.a(this.lm.isEmpty());
         Object[] var10000 = new Object[]{this.Uv};
         return this.Uv;
      }
   }

   private bbd q(String var1) {
      for (bch var3 : this.Uv) {
         if (var3.q != null && Strings.equals(var3.q.getName(false), var1)) {
            return var3.q;
         }
      }

      Object var4 = this.Dw.xK().q(var1);
      if (var4 == null) {
         var4 = this.Dw.q(var1, false);
         if (var4 == null) {
            gO.warn("Cannot find type: %s", var1);
         }
      }

      return (bbd)var4;
   }

   private bcf Uv() {
      if (this.lm.isEmpty()) {
         throw new IllegalStateException("Declaration stack is empty!");
      } else {
         return (bcf)this.lm.get(this.lm.size() - 1);
      }
   }

   private bcg oW() {
      bcf var1 = this.Uv();
      if (var1.oW.isEmpty()) {
         throw new IllegalStateException("Declarator stack of current declaration is empty!");
      } else {
         return (bcg)var1.oW.get(var1.oW.size() - 1);
      }
   }

   private Map gO() {
      bcf var1 = this.Uv();
      return var1.oW.isEmpty() ? var1.xK : ((bcg)var1.oW.get(var1.oW.size() - 1)).RF;
   }

   public static ParseTree q(ParseTree var0, int var1, Class var2) {
      return var0.getChild(var1);
   }

   public static ParseTree q(ParseTree var0, int var1) {
      return var0.getChild(var1);
   }

   void q(bcp.PY var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof bcp.CS) {
         this.RF((bcp.CS)var2);
      }
   }

   void q(bcp.CS var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof bcp.ST) {
         this.q((bcp.ST)var2);
      } else {
         if (!(var2 instanceof bcp.CS)) {
            throw new IllegalStateException();
         }

         this.q((bcp.CS)var2);
         this.q((bcp.ST)q(var1, 1, bcp.ST.class));
      }
   }

   void RF(bcp.CS var1) {
      ArrayList var2 = new ArrayList();

      Object var3;
      for (var3 = var1; var3.getChildCount() != 1; var3 = q((ParseTree)var3, 0, bcp.CS.class)) {
         var2.add(0, (bcp.ST)q((ParseTree)var3, 1, bcp.ST.class));
      }

      var3 = q((ParseTree)var3, 0, bcp.ST.class);
      var2.add(0, (bcp.ST)var3);
      int var4 = 0;

      for (bcp.ST var6 : var2) {
         if (var4 >= 10000000) {
            Object[] var10000 = new Object[]{var4};
            break;
         }

         try {
            if (!this.q(var6)) {
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

   boolean q(bcp.ST var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof bcp.Bu) {
         List var4 = this.q((bcp.Bu)var2);
         if (var4 == null) {
            return false;
         } else {
            this.Uv.addAll(var4);
            return true;
         }
      } else if (var2 instanceof bcp.Jh) {
         List var3 = this.q((bcp.Jh)var2);
         this.Uv.addAll(var3);
         return true;
      } else if (var2 instanceof bcp.Gw) {
         this.q((bcp.Gw)var2);
         return true;
      } else {
         return false;
      }
   }

   List q(bcp.Jh var1) {
      int var2 = 0;
      ParseTree var3 = q(var1, var2);
      if (!(var3 instanceof bcp.Uz)) {
         return null;
      } else {
         bcf var4 = new bcf();
         this.lm.add(var4);
         this.q((bcp.Uz)var3);
         var2++;
         this.Uv().oW.add(new bcg());
         bcp.kY var5 = (bcp.kY)q(var1, var2, bcp.kY.class);
         this.q(var5);
         q(var1, ++var2);
         this.lm.remove(var4);
         return this.q(var4);
      }
   }

   void q(bcp.Gw var1) {
      ParseTree var2 = var1.getChild(2);
      String var3 = var2.getText();
      ArrayList var4 = new ArrayList();
      if (var1.getChildCount() >= 6 && var1.getChild(3).getText().equals("(")) {
         bcp.sq var5 = (bcp.sq)q(var1, 4, bcp.sq.class);
         this.q(var5, var4);
      }

      if (var3.equals("pack")) {
         if (var4.isEmpty()) {
            this.RF();
         } else {
            String var9 = (String)var4.get(0);
            if (!var9.equals("show")) {
               if (Character.isDigit(var9.charAt(0))) {
                  int var6 = Conversion.stringToInt(var9);
                  this.q(var6);
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
                     this.q(var10, var7);
                  } else {
                     if (!var9.equals("pop")) {
                        throw new RuntimeException("Unsupported pragma pack(): " + var9);
                     }

                     this.RF(var10, var7);
                  }
               }
            }
         }
      } else {
         gO.warn("Unsupported pragma, ignoring: \"%s\"", this.q((ParseTree)var1));
      }
   }

   void q(bcp.sq var1, List var2) {
      ParseTree var3 = var1.getChild(0);
      if (var3 instanceof bcp.hG) {
         var2.add(var3.getText());
      } else {
         this.q((bcp.sq)var3, var2);
         var2.add(var1.getChild(2).getText());
      }
   }

   List q(bcp.Bu var1) {
      if (var1.getChildCount() == 1) {
         return null;
      } else {
         bcf var2 = new bcf();
         this.lm.add(var2);
         bcp.Uz var3 = (bcp.Uz)q(var1, 0, bcp.Uz.class);
         this.q(var3);
         ParseTree var4 = var1.getChild(1);
         if (var4 instanceof bcp.KM) {
            this.q((bcp.KM)var4);
         } else if (!var4.getText().equals(";")) {
            throw new IllegalStateException();
         }

         this.lm.remove(var2);
         return this.q(var2);
      }
   }

   List q(bcf var1) {
      Object[] var10000 = new Object[]{var1};
      if (var1.oW.size() == 0) {
         if (var1.Uv.size() < 2 && (var1.Uv.size() < 1 || var1.Dw.size() < 1 && var1.RF == null)) {
            bcg var9 = new bcg();
            var1.oW.add(var9);
         } else {
            String var2 = (String)var1.Uv.remove(var1.Uv.size() - 1);
            bcg var3 = new bcg();
            var1.oW.add(var3);
            var3.q.add(var2);
         }

         var10000 = new Object[]{var1};
      } else if (var1.RF == null && var1.Dw.isEmpty() && var1.Uv.isEmpty()) {
         Assert.a(var1.oW.size() == 1);
         String var10 = (String)((bcg)var1.oW.get(0)).q.remove(0);
         var1.Uv.add(var10);
         var10000 = new Object[]{var1};
      }

      if (!var1.Uv.isEmpty() && "operator".equals(var1.Uv.get(var1.Uv.size() - 1))) {
         String var11 = (String)var1.Uv.remove(var1.Uv.size() - 1);
         List var13 = ((bcg)var1.oW.get(0)).q;
         if (!var13.isEmpty()) {
            var13.set(0, var11 + " " + (String)var13.get(0));
         }
      }

      var1.Dw.addAll(var1.Uv);
      var1.Uv.clear();
      Object var12 = null;
      if (var1.RF != null) {
         Assert.a(var1.Dw.isEmpty());
         var12 = var1.RF;
      } else {
         Assert.a(!var1.Dw.isEmpty());
         String var14 = Strings.join(" ", var1.Dw);
         Boolean var4 = null;
         if (var14.equals("int")) {
            var4 = true;
         } else if (var14.equals("unsigned int")) {
            var4 = false;
         }

         if (var4 != null) {
            Integer var5 = (Integer)var1.xK.get("modeBitSize");
            if (var5 != null) {
               var12 = this.Dw.xK().q(var5 / 8, var4);
            }
         }

         if (var12 == null) {
            var12 = this.Dw.oW(var14);
         }
      }

      var10000 = new Object[]{var12};
      ArrayList var15 = new ArrayList();

      for (bcg var17 : var1.oW) {
         Object var6 = var12;

         while (!var17.xK.isEmpty()) {
            Object var7 = (bbd)var17.xK.remove(0);
            if (var7 instanceof bbt) {
               Assert.a(((bbt)var7).oW() == null);
               var7 = this.Dw.q((INativeType)var6, ((bbt)var7).getReferenceCount());
            } else if (var7 instanceof bbf) {
               Assert.a(((bbf)var7).oW() == null);
               var7 = this.Dw.RF((INativeType)var6, ((bbf)var7).getElementCount());
            } else {
               if (!(var7 instanceof bbs var8)) {
                  throw new RuntimeException();
               }

               var8.q((bbd)var6, false);
               this.q(var8, var17.RF);
            }

            var6 = var7;
         }

         if (var6 instanceof bbs var18) {
            this.q(var18, var1.xK);
         }

         String var19;
         if (var17.q.isEmpty()) {
            var19 = "__ANONYMOUS__";
         } else {
            var19 = (String)var17.q.get(0);
         }

         int var20 = 0;
         if (var17.Dw > 0) {
            var20 = var17.Dw;
         }

         if (var1.q) {
            var6 = this.Dw.q(var19, (INativeType)var6);
            var19 = null;
         }

         if (var6 instanceof bbs) {
            ((bbs)var6).q(var19);
         }

         var15.add(new bch((bbd)var6, var19, var20, this.RF(var1)));
      }

      return var15;
   }

   private void q(bbs var1, Map var2) {
      for (String var4 : var2.keySet()) {
         if (var4 != null) {
            if (CallingConventionName.find(var4) != null) {
               ICallingConvention var5 = this.Dw.Dw().getConvention(CallingConventionName.find(var4).toString());
               if (var5 == null) {
                  throw new RuntimeException("Unsupported or unknown calling convention: " + var4);
               }

               var1.q(var5);
            } else if (var4.equals("noreturn")) {
               var1.q(PrototypeAttribute.NoReturn, false);
            } else if (var4.equals("nothrow")) {
               var1.q(PrototypeAttribute.NoThrow, false);
            } else if (var4.equals("leaf")) {
               var1.q(PrototypeAttribute.Leaf, false);
            } else if (var4.equals("pure")) {
               var1.q(PrototypeAttribute.Pure, false);
            } else if (var4.equals("const")) {
               var1.q(PrototypeAttribute.Const, false);
            } else if (var4.equals("volatile")) {
               var1.q(PrototypeAttribute.Volatile, false);
            } else if (!Strings.isContainedIn(var4, "extern", "static", "inline", "__inline", "__inline__", "forceinline", "__forceinline", "__forceinline__")) {
               gO.debug("Unsupported specifier in prototype: %s", var4);
            }
         }
      }
   }

   void q(bcp.KM var1) {
      if (var1.getChildCount() == 1) {
         this.q((bcp.zQ)q(var1, 0, bcp.zQ.class));
      } else {
         if (var1.getChildCount() != 3) {
            throw new RuntimeException();
         }

         this.q((bcp.KM)q(var1, 0, bcp.KM.class));
         this.q((bcp.zQ)q(var1, 2, bcp.zQ.class));
      }
   }

   void q(bcp.zQ var1) {
      if (var1.getChildCount() == 1) {
         this.Uv().oW.add(new bcg());
         this.q((bcp.kY)q(var1, 0, bcp.kY.class));
      } else {
         this.Uv().oW.add(new bcg());
         this.q((bcp.kY)q(var1, 0, bcp.kY.class));
      }
   }

   void q(bcp.Uz var1) {
      for (ParseTree var3 : var1.children) {
         this.q((bcp.tl)var3);
      }
   }

   void q(bcp.tl var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof bcp.Rz) {
         this.q((bcp.Rz)var2);
      } else if (var2 instanceof bcp.Oj) {
         this.q((bcp.Oj)var2);
      } else if (var2 instanceof bcp.Cg) {
         this.q((bcp.Cg)var2);
      } else {
         if (!(var2 instanceof bcp.Rd)) {
            throw new bce(var2);
         }

         this.q((bcp.Rd)var2);
      }
   }

   void q(bcp.Rz var1) {
      if (var1.getChildCount() != 1) {
         throw new IllegalStateException();
      } else {
         ParseTree var2 = var1.getChild(0);
         if (var2.getChildCount() != 0) {
            throw new IllegalStateException();
         } else {
            String var3 = var2.getText();
            if (var3.equals("typedef")) {
               this.Uv().q = true;
            } else {
               if (!Strings.isContainedIn(var3, "extern", "static", "auto", "register")) {
                  throw new bce(var2);
               }

               this.gO().put(var3, null);
            }
         }
      }
   }

   void q(bcp.Oj var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof TerminalNode) {
         String var3 = var2.getText();
         this.Uv().Dw.add(var3);
      } else if (var2 instanceof bcp.XR) {
         this.Uv().RF = this.q((bcp.XR)var2);
      } else if (var2 instanceof bcp.Ff) {
         this.q((bcp.Ff)var2);
      } else if (var2 instanceof bcp.Fw) {
         this.Uv().RF = this.q((bcp.Fw)var2);
      } else {
         if (!(var2 instanceof bcp.tw)) {
            throw new bce(var2);
         }

         String var4 = this.q((bcp.eV)q(var2, 2, bcp.eV.class));
         this.Uv().Dw.add(var4);
      }
   }

   void q(bcp.Ff var1) {
      String var2 = var1.getChild(0).getText();
      this.Uv().Uv.add(var2);
   }

   void q(bcp.XC var1) {
      if (var1.getChildCount() == 1) {
         this.q((bcp.Cg)q(var1, 0, bcp.Cg.class));
      } else {
         this.q((bcp.XC)q(var1, 0, bcp.XC.class));
         this.q((bcp.Cg)q(var1, 1, bcp.Cg.class));
      }
   }

   void q(bcp.Cg var1) {
      String var2 = var1.getText();
      if (!var2.equals("__extension__")) {
         this.gO().put(var2, null);
      }
   }

   void q(bcp.Rd var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2.getChildCount() == 0) {
         String var3 = var2.getText();
         this.gO().put(var3, null);
      } else if (var2 instanceof bcp.Xa) {
         this.q((bcp.Xa)var2);
      } else {
         if (!(var2 instanceof bcp.fC)) {
            throw new IllegalStateException();
         }

         this.q((bcp.fC)var2);
      }
   }

   void q(bcp.fC var1) {
      bcp.Ua var2 = (bcp.Ua)q(var1, 3, bcp.Ua.class);
      this.q(var2);
   }

   void q(bcp.Ua var1) {
      for (byte var2 = 0; var2 < var1.getChildCount(); var2 += 2) {
         this.q((bcp.Pl)q(var1, var2, bcp.Pl.class));
      }
   }

   void q(bcp.Pl var1) {
      String var2 = this.q((ParseTree)var1);
      if (var2.startsWith("__mode__")) {
         String var3 = var2.substring(8).trim();
         if (var3.startsWith("(") && var3.endsWith(")")) {
            var3 = var3.substring(1, var3.length() - 1).trim();
            Integer var4 = null;
            switch (var3) {
               case "__word__":
                  var4 = this.Dw.getSlotSize() * 8;
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
               this.gO().put("modeBitSize", var4);
            }
         }
      }

      if (var2.startsWith("__") && var2.endsWith("__")) {
         var2 = var2.substring(2, var2.length() - 2);
      }

      if (var2.equals("noreturn")) {
         this.gO().put("noreturn", true);
      } else if (var2.equals("nothrow")) {
         this.gO().put("nothrow", true);
      } else if (var2.equals("leaf")) {
         this.gO().put("leaf", true);
      } else if (var2.equals("pure")) {
         this.gO().put("pure", true);
      } else if (var2.equals("const")) {
         this.gO().put("const", true);
      } else if (var2.startsWith("aligned")) {
         String var8 = var2.substring(7).trim();
         var8 = Strings.ltrim(var8, '(');
         var8 = Strings.rtrim(var8, ')');
         var8 = var8.trim();
         int var12 = Conversion.stringToInt(var8);
         this.gO().put("align", var12);
      } else {
         Object[] var10000 = new Object[]{var2};
      }
   }

   void q(bcp.kY var1) {
      int var2 = 0;

      ParseTree var3;
      for (var3 = var1.getChild(var2); var3 instanceof bcp.YN; var3 = var1.getChild(++var2)) {
         this.q((bcp.YN)var3);
      }

      if (var3 instanceof bcp.yG) {
         this.q((bcp.yG)var3);
         var3 = var1.getChild(++var2);
      }

      while (var3 instanceof bcp.YN) {
         this.q((bcp.YN)var3);
         var3 = var1.getChild(++var2);
      }

      this.q((bcp.ME)var3);
      var2++;

      while (var2 < var1.getChildCount()) {
         var3 = var1.getChild(var2);
         this.q((bcp.bd)var3);
         var2++;
      }
   }

   void q(bcp.YN var1) {
      ParseTree var2 = var1.getChild(0);
      if (!(var2 instanceof TerminalNode)) {
         this.q((bcp.Rd)q(var1, 0, bcp.Rd.class));
      }
   }

   void q(bcp.bd var1) {
      if (var1.getChildCount() == 1) {
         this.q((bcp.fC)q(var1, 0, bcp.fC.class));
      }
   }

   void q(bcp.yG var1) {
      ParseTree var2 = var1.getChild(0);
      String var3 = var2.getText();
      if (!var3.equals("*") && !var3.equals("&")) {
         throw new bce(var1);
      } else {
         this.oW().xK.add(this.Dw.q(null, 1));
         if (var1.getChildCount() != 1) {
            ParseTree var4 = var1.getChild(1);
            if (var4 instanceof bcp.XC) {
               this.q((bcp.XC)var4);
               if (var1.getChildCount() != 2) {
                  bcp.yG var5 = (bcp.yG)q(var1, 2, bcp.yG.class);
                  this.q(var5);
               }
            } else {
               this.q((bcp.yG)var4);
            }
         }
      }
   }

   void q(bcp.ME var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof TerminalNode) {
         String var8 = var2.getText();
         if (var8.equals("(")) {
            if (!var1.getChild(2).getText().equals(")")) {
               throw new IllegalStateException();
            }

            bcp.kY var10 = (bcp.kY)q(var1, 1, bcp.kY.class);
            this.q(var10);
         } else {
            this.oW().q.add(var8);
         }
      } else if (!(var2 instanceof bcp.ME)) {
         throw new bce(var1);
      } else {
         ParseTree var3 = var1.getChild(1);
         if (var3 instanceof TerminalNode && var3.getText().equals("[")) {
            int var11 = 2;
            ParseTree var6 = var1.getChild(var11);
            if (var6 instanceof bcp.XC) {
               var11++;
            }

            var6 = var1.getChild(var11);
            int var9;
            if (var6 instanceof TerminalNode && var6.getText().equals("]")) {
               var9 = 0;
            } else {
               if (!(var6 instanceof bcp.Nt)) {
                  throw new RuntimeException("Limited subset of array declarators are handled (2)");
               }

               if (!var1.getChild(var11 + 1).getText().equals("]")) {
                  throw new RuntimeException("Limited subset of array declarators are handled");
               }

               Object var7 = this.q((bcp.Nt)var6);
               if (var7 == null || !(var7 instanceof Integer)) {
                  throw new RuntimeException("Cannot evaluate array size");
               }

               var9 = (Integer)var7;
            }

            if (var9 < 0) {
               throw new RuntimeException("Cannot evaluate array size");
            }

            Object var13;
            if (!this.zz) {
               var13 = this.Dw.RF(null, var9);
            } else {
               var13 = this.Dw.q(null, 1);
            }

            this.oW().xK.add(var13);
            this.q((bcp.ME)var2);
         } else {
            if (!var3.getText().equals("(")) {
               throw new bce(var1);
            }

            bbs var4 = this.Dw.za();
            this.oW().xK.add(var4);
            ParseTree var5 = var1.getChild(2);
            if (var5.getChildCount() != 0 || !var5.getText().equals(")")) {
               if (!var1.getChild(3).getText().equals(")")) {
                  throw new RuntimeException("Limited subset of function declarators are handled");
               }

               this.q((bcp.xI)var5, var4);
            }

            this.q((bcp.ME)var2);
         }
      }
   }

   void q(bcp.xI var1, bbs var2) {
      if (var1.getChildCount() != 1) {
         if (!var1.getChild(2).getText().equals("...")) {
            throw new IllegalStateException();
         }

         var2.q(PrototypeAttribute.VarArg, false);
      }

      bcp.TE var3 = (bcp.TE)q(var1, 0, bcp.TE.class);
      this.q(var3, var2);
   }

   void q(bcp.TE var1, bbs var2) {
      if (var1.getChildCount() == 1) {
         this.q((bcp.As)q(var1, 0, bcp.As.class), var2);
      } else {
         this.q((bcp.TE)q(var1, 0, bcp.TE.class), var2);
         this.q((bcp.As)q(var1, 2, bcp.As.class), var2);
      }
   }

   void q(bcp.As var1, bbs var2) {
      bcf var3 = new bcf();
      this.lm.add(var3);
      this.zz = true;
      ParseTree var4 = var1.getChild(0);
      if (var4 instanceof bcp.Uz) {
         this.q((bcp.Uz)var4);
         var3.oW.add(new bcg());
         this.q((bcp.kY)q(var1, 1, bcp.kY.class));
      } else {
         if (!(var4 instanceof bcp.Nz)) {
            throw new IllegalStateException();
         }

         this.q((bcp.Nz)var4);
         if (var1.getChildCount() >= 2) {
            var3.oW.add(new bcg());
            this.q((bcp.eo)q(var1, 1, bcp.eo.class));
         }
      }

      this.zz = false;
      this.lm.remove(var3);
      List var5 = this.q(var3);
      if (var5.size() != 1 || !TypeUtil.isVoid(((bch)var5.get(0)).q)) {
         int var6 = 1 + var2.getCountOfParameters();

         for (bch var8 : var5) {
            bbd var9 = var8.q;
            String var10 = var8.RF;
            if (var10 == null || var10.equals("__ANONYMOUS__")) {
               var10 = "param" + var6;
            }

            var2.q(var9, var10, false);
            var6++;
         }
      }
   }

   void q(bcp.Nz var1) {
      for (ParseTree var3 : var1.children) {
         this.q((bcp.tl)var3);
      }
   }

   void q(bcp.eo var1) {
      ParseTree var2 = var1.getChild(0);
      if (var1.getChildCount() == 1 && var2 instanceof bcp.yG) {
         this.q((bcp.yG)var2);
      } else {
         if (!(var2 instanceof bcp.yG)) {
            if (var2 instanceof bcp.qa) {
               this.q((bcp.qa)var2);
               if (var1.getChildCount() != 1) {
                  throw new bce(var1);
               }

               return;
            }

            throw new bce(var1);
         }

         this.q((bcp.yG)var2);
         bcp.qa var3 = (bcp.qa)q(var1, 1, bcp.qa.class);
         this.q(var3);
      }
   }

   void q(bcp.qa var1) {
      throw new bce(var1);
   }

   bbm q(bcp.Fw var1) {
      ParseTree var2 = var1.getChild(0);
      Assert.a(var2.getText().equals("enum"));
      if (var1.getChildCount() == 2) {
         String var12 = var1.getChild(1).getText();
         bbd var14 = this.Dw.Uv(var12);
         bbm var15 = (bbm)TypeUtil.getNonAlias(var14, bbm.class);
         if (var15 != null) {
            return var15;
         } else if (!this.xK.RF()) {
            throw new IllegalStateException("Creation of new complex type is denied: " + var12);
         } else {
            var15 = this.Dw.q(var12);
            this.oW.add(var15);
            return var15;
         }
      } else {
         String var3 = null;
         int var4 = 1;
         ParseTree var5 = var1.getChild(var4);
         if (var5 instanceof bcp.Xa) {
            this.q((bcp.Xa)var5);
            var5 = var1.getChild(++var4);
         }

         if (!(var5 instanceof TerminalNode)) {
            throw new IllegalStateException();
         } else {
            if (!var5.getText().equals("{")) {
               var3 = var5.getText();
               var5 = var1.getChild(++var4);
            }

            if (var5 instanceof bcp.SG) {
               gO.debug("C++11 enum-base is ignored in: \"%s\"", this.q((ParseTree)var1));
               var5 = var1.getChild(++var4);
            }

            if (!var5.getText().equals("{")) {
               throw new IllegalStateException();
            } else {
               var4++;
               boolean var6 = false;
               if (var3 == null) {
                  var3 = "__synth_enum_" + this.Dw.getTypes().size();
                  var6 = true;
               }

               bcp.qx var7 = (bcp.qx)q(var1, var4, bcp.qx.class);
               int var9 = 0;
               String var10 = var3;

               while (true) {
                  bbd var11 = this.Dw.Uv(var3);
                  bbm var8 = (bbm)TypeUtil.getNonAlias(var11, bbm.class);
                  if (var8 == null || var8.getConstants().isEmpty()) {
                     if (var9 >= 1) {
                        Object[] var10000 = new Object[]{var10, var3};
                     }

                     if (var8 == null) {
                        if (!this.xK.RF()) {
                           throw new IllegalStateException("Creation of new complex type is denied: " + var3);
                        }

                        var8 = this.Dw.q(var3);
                        if (var6) {
                           var8.RF(2097152, false);
                        }
                     }

                     this.q(var7, var8);
                     Object[] var17 = new Object[]{var8, var8.getConstants().size()};
                     return var8;
                  }

                  if (!gP) {
                     throw new RuntimeException("Enum defined already");
                  }

                  var3 = var10 + "_copy" + ++var9;
               }
            }
         }
      }
   }

   void q(bcp.qx var1, bbm var2) {
      ParseTree var3 = var1.getChild(0);
      if (var3 instanceof bcp.GA) {
         this.q((bcp.GA)var3, var2);
      } else {
         this.q((bcp.qx)var3, var2);
         this.q((bcp.GA)q(var1, 2, bcp.GA.class), var2);
      }
   }

   void q(bcp.GA var1, bbm var2) {
      bcp.ct var3 = (bcp.ct)q(var1, 0, bcp.ct.class);
      String var4 = var3.getText();
      if (var1.getChildCount() >= 3) {
         ParseTree var5 = var1.getChild(2);
         String var6 = var5.getText();
         bbl var8 = var2.RF(var6);
         int var7;
         if (var8 != null) {
            var7 = var8.getValue();
         } else {
            var7 = Conversion.stringToInt(var6);
         }

         var2.q(var4, var7);
      } else {
         var2.q(var4);
      }
   }

   bbv q(bcp.XR var1) {
      ParseTree var2 = var1.getChild(0);
      boolean var3 = var2.getText().equals("union");
      if (var1.getChildCount() == 2) {
         String var12 = var1.getChild(1).getText();
         bbd var14 = this.Dw.Uv(var12);
         bbv var16 = (bbv)TypeUtil.getNonAlias(var14, bbv.class);
         if (var16 != null) {
            return var16;
         } else if (!this.xK.RF()) {
            throw new IllegalStateException("Creation of new complex type is denied: " + var12);
         } else {
            var16 = this.Dw.q(var12, var3 ? 0 : this.xK(), this.RF(this.Uv()));
            this.oW.add(var16);
            return var16;
         }
      } else {
         String var4 = null;
         int var5 = 1;
         ParseTree var6 = var1.getChild(var5);
         if (var6 instanceof bcp.CI) {
            this.q((bcp.CI)var6);
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
                  var4 = "__synth_struct_" + this.Dw.getTypes().size();
                  var7 = true;
               }

               int var9 = 0;
               String var10 = var4;

               while (true) {
                  bbd var11 = this.Dw.Uv(var4);
                  bbv var8 = (bbv)TypeUtil.getNonAlias(var11, bbv.class);
                  if (var8 == null || var8.getFields().isEmpty()) {
                     if (var9 >= 1) {
                        Object[] var10000 = new Object[]{var10, var4};
                     }

                     if (var8 == null) {
                        if (!this.xK.RF()) {
                           throw new IllegalStateException("Creation of new complex type is denied: " + var4);
                        }

                        var8 = this.Dw.q(var4, var3 ? 0 : this.xK(), this.RF(this.Uv()));
                        if (var7) {
                           var8.RF(2097152, false);
                        }
                     }

                     var6 = var1.getChild(var5);
                     if (var6 instanceof bcp.gM var18) {
                        this.q(var18, var8);
                     } else if (!var6.getText().equals("}")) {
                        throw new RuntimeException();
                     }

                     Object[] var19 = new Object[]{var8, var8.getFields().size()};
                     return var8;
                  }

                  if (!za) {
                     throw new RuntimeException("Struct/union defined already");
                  }

                  var4 = var10 + "_copy" + ++var9;
               }
            }
         }
      }
   }

   private int RF(bcf var1) {
      Object var2 = var1.xK.get("align");
      return var2 instanceof Integer ? (Integer)var2 : 0;
   }

   void q(bcp.CI var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof bcp.Xa) {
         this.q((bcp.Xa)var2);
      } else {
         this.q((bcp.CI)var2);
         this.q((bcp.Xa)q(var1, 1, bcp.Xa.class));
      }
   }

   void q(bcp.Xa var1) {
      bcp.KZ var2 = (bcp.KZ)q(var1, 2, bcp.KZ.class);
      this.q(var2);
   }

   void q(bcp.KZ var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof bcp.zJ) {
         this.q((bcp.zJ)var2);
      } else {
         this.q((bcp.KZ)var2);
         this.q((bcp.zJ)q(var1, 1, bcp.zJ.class));
      }
   }

   void q(bcp.zJ var1) {
      bcp.vX var2 = (bcp.vX)q(var1, 0, bcp.vX.class);
      String var3 = var2.getText();
      if (var3.equals("align")) {
         int var4 = Conversion.stringToInt(var1.getChild(2).getText());
         Object[] var10000 = new Object[]{var4};
         this.gO().put(var3, var4);
      } else if (var3.equals("noreturn")) {
         Object[] var5 = new Object[0];
         this.gO().put(var3, true);
      } else {
         Object[] var6 = new Object[]{var3};
      }
   }

   void q(bcp.gM var1, bbv var2) {
      if (var1.getChildCount() == 1) {
         this.q((bcp.Kn)q(var1, 0, bcp.Kn.class), var2);
      } else {
         if (var1.getChildCount() != 2) {
            throw new RuntimeException();
         }

         this.q((bcp.gM)q(var1, 0, bcp.gM.class), var2);
         this.q((bcp.Kn)q(var1, 1, bcp.Kn.class), var2);
      }
   }

   void q(bcp.Kn var1, bbv var2) {
      if (var1.getChildCount() == 1) {
         ParseTree var11 = var1.getChild(0);
         if (var11 instanceof bcp.Gw) {
            this.q((bcp.Gw)q(var1, 0, bcp.Gw.class));
         } else if (!var11.toString().equals(";")) {
            throw new RuntimeException("TBI: assert() statement in structure");
         }
      } else {
         bcf var3 = new bcf();
         this.lm.add(var3);
         bcp.Lk var4 = (bcp.Lk)q(var1, 0, bcp.Lk.class);
         this.q(var4);
         int var5 = 1;
         ParseTree var6 = var1.getChild(var5);
         if (var6 instanceof bcp.iY) {
            this.q((bcp.iY)var6);
            var5++;
         }

         while (var5 < var1.getChildCount()) {
            var6 = var1.getChild(var5);
            if (!(var6 instanceof bcp.bd)) {
               break;
            }

            var5++;
         }

         if (var5 < var1.getChildCount() && var1.getChild(var5).toString().equals(";")) {
            this.lm.remove(var3);

            for (bch var9 : this.q(var3)) {
               int var10 = 0;
               if ("__ANONYMOUS__".equals(var9.RF)) {
                  var10 |= 2097152;
               }

               var2.q(var9.RF, var9.q, -1, var9.xK, var9.Dw, var10);
            }
         } else {
            throw new RuntimeException();
         }
      }
   }

   void q(bcp.Lk var1) {
      ParseTree var2 = var1.getChild(0);
      if (var2 instanceof bcp.Oj) {
         this.q((bcp.Oj)var2);
      } else if (var2 instanceof bcp.Cg) {
         this.q((bcp.Cg)var2);
      }

      if (var1.getChildCount() == 2) {
         this.q((bcp.Lk)q(var1, 1, bcp.Lk.class));
      }
   }

   void q(bcp.iY var1) {
      if (var1.getChildCount() == 1) {
         this.q((bcp.wN)q(var1, 0, bcp.wN.class));
      } else {
         if (var1.getChildCount() != 3) {
            throw new RuntimeException();
         }

         this.q((bcp.iY)q(var1, 0, bcp.iY.class));
         this.q((bcp.wN)q(var1, 2, bcp.wN.class));
      }
   }

   void q(bcp.wN var1) {
      this.Uv().oW.add(new bcg());
      int var2 = var1.getChildCount();
      if (var2 == 1 || var2 == 3) {
         bcp.kY var3 = (bcp.kY)q(var1, 0, bcp.kY.class);
         this.q(var3);
      }

      if (var2 >= 2) {
         byte var7 = 2;
         if (var2 == 2) {
            this.oW().q.add("_");
            var7 = 1;
         }

         bcp.Vj var4 = (bcp.Vj)q(var1, var7, bcp.Vj.class);
         Object var5 = this.q(var4);
         if (!(var5 instanceof Integer)) {
            throw new RuntimeException();
         }

         int var6 = (Integer)var5;
         if (var6 <= 0) {
            throw new RuntimeException();
         }

         this.oW().Dw = var6;
      }
   }

   boolean q(Object var1) {
      if (var1 instanceof Boolean) {
         return (Boolean)var1;
      } else {
         throw new RuntimeException("TBI: evalAsBool for object: " + var1);
      }
   }

   Object q(bcp.Nt var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.oL)q(var1, 0, bcp.oL.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object q(bcp.Vj var1) {
      bcp.oL var2 = (bcp.oL)q(var1, 0, bcp.oL.class);
      return this.q(var2);
   }

   Object q(bcp.oL var1) {
      bcp.TN var2 = (bcp.TN)q(var1, 0, bcp.TN.class);
      Object var3 = this.q(var2);
      if (var1.getChildCount() >= 2) {
         if (this.q(var3)) {
            var3 = this.q((bcp.Zu)q(var1, 2, bcp.Zu.class));
         } else {
            var3 = this.q((bcp.oL)q(var1, 4, bcp.oL.class));
         }
      }

      return var3;
   }

   Object q(bcp.TN var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.CK)q(var1, 0, bcp.CK.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object q(bcp.CK var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.ap)q(var1, 0, bcp.ap.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object q(bcp.ap var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.ry)q(var1, 0, bcp.ry.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object q(bcp.ry var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.ej)q(var1, 0, bcp.ej.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object q(bcp.ej var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.FL)q(var1, 0, bcp.FL.class));
      } else {
         throw new RuntimeException();
      }
   }

   Object q(bcp.FL var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.Jr)q(var1, 0, bcp.Jr.class));
      } else {
         Object var2 = this.q((bcp.FL)q(var1, 0, bcp.FL.class));
         Object var3 = this.q((bcp.Jr)q(var1, 2, bcp.Jr.class));
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

   Object q(bcp.Jr var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.gv)q(var1, 0, bcp.gv.class));
      } else {
         Object var2 = this.q((bcp.Jr)q(var1, 0, bcp.Jr.class));
         Object var3 = this.q((bcp.gv)q(var1, 2, bcp.gv.class));
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

   Object q(bcp.gv var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.CU)q(var1, 0, bcp.CU.class));
      } else {
         String var2 = var1.getChild(1).getText();
         bcc.eo var3 = var2.equals("<<") ? bcc.eo.oW : bcc.eo.gO;
         Object var4 = this.q((bcp.gv)q(var1, 0, bcp.gv.class));
         Object var5 = this.q((bcp.CU)q(var1, 2, bcp.CU.class));
         return bcc.q(var3, var4, var5);
      }
   }

   Object q(bcp.CU var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.EU)q(var1, 0, bcp.EU.class));
      } else {
         String var2 = var1.getChild(1).getText();
         bcc.eo var3 = var2.equals("+") ? bcc.eo.q : bcc.eo.RF;
         Object var4 = this.q((bcp.CU)q(var1, 0, bcp.CU.class));
         Object var5 = this.q((bcp.EU)q(var1, 2, bcp.EU.class));
         return bcc.q(var3, var4, var5);
      }
   }

   Object q(bcp.EU var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.vn)q(var1, 0, bcp.vn.class));
      } else {
         String var2 = var1.getChild(1).getText();
         bcc.eo var3 = var2.equals("*") ? bcc.eo.xK : (var2.equals("/") ? bcc.eo.Dw : bcc.eo.Uv);
         Object var4 = this.q((bcp.EU)q(var1, 0, bcp.EU.class));
         Object var5 = this.q((bcp.vn)q(var1, 2, bcp.vn.class));
         return bcc.q(var3, var4, var5);
      }
   }

   Object q(bcp.vn var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.Yd)q(var1, 0, bcp.Yd.class));
      } else {
         bcp.vn var2 = (bcp.vn)q(var1, var1.getChildCount() - 1, bcp.vn.class);
         return this.q(var2);
      }
   }

   Object q(bcp.Yd var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.if)q(var1, 0, bcp.if.class));
      } else {
         if (var1.getChildCount() >= 2) {
            String var2 = var1.getChild(0).getText();
            if (var2.equals("sizeof") || var2.equals("__alignof__")) {
               bbd var3 = null;
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

                     var3 = this.q(var5);
                  }
               } else if (var1.getChildCount() == 4) {
                  String var9 = this.q((bcp.eV)q(var1, 2, bcp.eV.class));
                  var3 = this.q(var9);
               }

               if (var3 != null) {
                  return var3.getSize();
               }
            }
         }

         throw new RuntimeException("eval() error: " + var1.getText());
      }
   }

   Object q(bcp.if var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.hG)q(var1, 0, bcp.hG.class));
      } else {
         throw new RuntimeException("eval() error: " + var1.getText());
      }
   }

   Object q(bcp.hG var1) {
      if (var1.getChildCount() == 1) {
         String var2 = var1.getChild(0).getText();
         if (var2.endsWith("U")) {
            var2 = var2.substring(0, var2.length() - 1);
         }

         int var3 = Conversion.stringToInt(var2, -1);
         if (var3 != -1) {
            return var3;
         }

         Iterator var4 = this.Dw.zz();

         while (var4.hasNext()) {
            bbl var5 = ((bbm)var4.next()).RF(var2);
            if (var5 != null) {
               return var5.getValue();
            }
         }
      } else if (var1.getChildCount() == 3 && var1.getChild(1) instanceof bcp.Zu) {
         return this.q((bcp.Zu)q(var1, 1, bcp.Zu.class));
      }

      throw new RuntimeException("eval() error: " + var1.getText());
   }

   Object q(bcp.Zu var1) {
      if (var1.getChildCount() == 1) {
         return this.q((bcp.Nt)q(var1, 0, bcp.Nt.class));
      } else {
         throw new RuntimeException("eval() error: " + var1.getText());
      }
   }

   String q(bcp.eV var1) {
      String var2 = this.q((ParseTree)var1);
      if (var2.startsWith("struct")) {
         var2 = var2.substring(6);
      } else if (var2.startsWith("union")) {
         var2 = var2.substring(5);
      }

      return var2.trim();
   }

   private String q(ParseTree var1) {
      StringBuilder var2 = new StringBuilder();
      this.q(var1, var2);
      return var2.toString().trim();
   }

   private void q(ParseTree var1, StringBuilder var2) {
      if (var1 instanceof TerminalNode) {
         var2.append(var1);
         var2.append(' ');
      } else if (var1 instanceof RuleNode) {
         int var3 = var1.getChildCount();

         for (int var4 = 0; var4 < var3; var4++) {
            ParseTree var5 = var1.getChild(var4);
            this.q(var5, var2);
         }
      }
   }
}
