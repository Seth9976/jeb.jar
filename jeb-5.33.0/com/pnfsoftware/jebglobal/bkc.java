package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.exceptions.JebRuntimeException;
import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.units.code.android.JvmFieldSig;
import com.pnfsoftware.jeb.core.units.code.java.IDynamicContentManager;
import com.pnfsoftware.jeb.core.units.code.java.IJavaBlock;
import com.pnfsoftware.jeb.core.units.code.java.IJavaElement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaExpression;
import com.pnfsoftware.jeb.core.units.code.java.IJavaField;
import com.pnfsoftware.jeb.core.units.code.java.IJavaStatement;
import com.pnfsoftware.jeb.core.units.code.java.IJavaSwitch;
import com.pnfsoftware.jeb.core.units.code.java.JavaElementType;
import com.pnfsoftware.jeb.core.units.code.java.JavaKeyword;
import com.pnfsoftware.jeb.core.units.code.java.JavaOutputSink;
import com.pnfsoftware.jeb.util.collect.Maps;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.logging.GlobalLog;
import com.pnfsoftware.jeb.util.logging.ILogger;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerCustomInitPostGraph;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;

@Ser
public class bkc extends bil implements IJavaSwitch {
   private static final ILogger wS = GlobalLog.getLogger(bkc.class);
   @SerId(1)
   private IJavaExpression UT;
   @SerId(2)
   private Map E;
   @SerId(3)
   private bir sY;
   @SerId(4)
   private Map ys;
   @SerId(value = 5, deprecated = true)
   @Deprecated
   private Map ld;
   @SerId(6)
   private Map gp;
   @SerId(7)
   private Map oT;

   @SerCustomInitPostGraph
   private void UT() {
      if (!(this.E instanceof IdentityHashMap)) {
         this.E = new IdentityHashMap(this.E);
      }

      if (this.ld != null) {
         this.gp = new HashMap();
         this.ld.forEach((var1, var2) -> this.gp.put(var1, var2.getSignature()));
         this.ld = null;
      }
   }

   public bkc(IJavaExpression var1, int var2) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.UT = var1;
         this.E = new IdentityHashMap();
         this.sY = null;
         if (var2 == 1) {
            this.gp = new HashMap();
         } else if (var2 == 2) {
            this.ys = new HashMap();
         } else if (var2 != 0) {
            throw new RuntimeException("Illegal switch type: " + var2);
         }
      }
   }

   public bkc(IJavaExpression var1) {
      this(var1, 0);
   }

   @Override
   public IJavaExpression getSwitchedExpression() {
      return this.UT;
   }

   @Override
   public int getSwitchType() {
      if (this.isSwitchOnInteger()) {
         return 0;
      } else if (this.isSwitchOnEnum()) {
         return 1;
      } else if (this.isSwitchOnString()) {
         return 2;
      } else {
         throw new RuntimeException();
      }
   }

   @Override
   public void setSwitchedExpression(IJavaExpression var1) {
      if (var1 == null) {
         throw new IllegalArgumentException();
      } else {
         this.UT = var1;
      }
   }

   @Override
   public SortedSet getCaseKeys() {
      TreeSet var1 = new TreeSet();

      for (List var3 : this.E.values()) {
         var1.addAll(var3);
      }

      return var1;
   }

   public SortedSet pC(bir var1) {
      TreeSet var2 = new TreeSet();

      for (Integer var4 : (List)this.E.get(var1)) {
         var2.add((String)this.ys.get(var4));
      }

      return var2;
   }

   @Override
   public void convertToSwitchOnInteger(IJavaExpression var1) {
      if (var1 != null) {
         this.UT = var1;
      }

      this.ys = null;
      this.gp = null;
   }

   @Override
   public void convertToSwitchOnEnum(IJavaExpression var1, Map var2) {
      if (var1 != null) {
         this.UT = var1;
      }

      this.ys = null;
      this.gp = var2;
   }

   @Override
   public void convertToSwitchOnString(IJavaExpression var1, Map var2) {
      if (!var2.keySet().equals(this.getCaseKeys())) {
         throw new IllegalArgumentException();
      } else {
         for (String var4 : var2.values()) {
            if (var4 == null) {
               throw new IllegalArgumentException();
            }
         }

         if (var1 != null) {
            this.UT = var1;
         }

         this.ys = var2;
         this.gp = null;
      }
   }

   @Override
   public boolean isSwitchOnInteger() {
      return this.ys == null && this.gp == null;
   }

   @Override
   public boolean isSwitchOnEnum() {
      return this.ys == null && this.gp != null;
   }

   @Override
   public boolean isSwitchOnString() {
      return this.ys != null && this.gp == null;
   }

   private void E() {
      if (!this.isSwitchOnEnum()) {
         throw new IllegalStateException("Not a switch-on-enum");
      }
   }

   private void sY() {
      if (!this.isSwitchOnString()) {
         throw new IllegalStateException("Not a switch-on-string");
      }
   }

   @Override
   public Map getEnumMap() {
      this.E();
      return this.gp;
   }

   @Override
   public Map getStringMap() {
      this.sY();
      return this.ys;
   }

   @Override
   public List getCaseBodies() {
      return new ArrayList(this.E.keySet());
   }

   public bir pC(int var1) {
      for (bir var3 : this.E.keySet()) {
         if (((List)this.E.get(var3)).contains(var1)) {
            return var3;
         }
      }

      return null;
   }

   public bir A(String var1) {
      this.sY();
      Integer var2 = null;

      for (Entry var4 : this.ys.entrySet()) {
         if (((String)var4.getValue()).equals(var1)) {
            var2 = (Integer)var4.getKey();
            break;
         }
      }

      return var2 == null ? null : this.pC(var2);
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.sY != null;
   }

   public bir A() {
      return this.sY;
   }

   @Override
   public void addCase(List var1, IJavaBlock var2) {
      if (var2 != null && !var1.isEmpty()) {
         this.E.put((bir)var2, var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void pC(List var1, IJavaBlock var2) {
      for (Object var4 : var1) {
         if (var4 instanceof Integer) {
            List var5 = Maps.addMulti(this.E, (bir)var2);
            var5.add((Integer)var4);
         } else if (var4 instanceof String) {
            int var7 = this.ys.size();
            this.ys.put(var7, (String)var4);
            List var6 = Maps.addMulti(this.E, (bir)var2);
            var6.add(var7);
         }
      }
   }

   @Override
   public void addCase(Map var1, IJavaBlock var2) {
      this.sY();
      if (var2 != null && !var1.isEmpty()) {
         this.E.put((bir)var2, new ArrayList(var1.keySet()));
         this.ys.putAll(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void setDefaultBlock(IJavaBlock var1) {
      this.sY = (bir)var1;
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      for (bir var4 : this.E.keySet()) {
         if (var4.insertAt(var1, var2)) {
            return true;
         }
      }

      return this.sY != null ? this.sY.insertAt(var1, var2) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList(this.E.keySet());
      if (this.sY != null) {
         var1.add(this.sY);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new boc(this));

      for (bir var4 : this.kS(null)) {
         var1.add(new bod((List)this.E.get(var4), this.isSwitchOnString() ? new ArrayList(this.pC(var4)) : null));
         var1.addAll(var4.generateFlatList());
      }

      if (this.sY != null) {
         var1.add(new boe());
         var1.addAll(this.sY.generateFlatList());
      }

      var1.add(new bof());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      List var2 = bhr.pC(this.UT);
      if (this.oT != null) {
         bhr.pC(var2, this.oT.values());
      }

      if (!var1) {
         bhr.pC(var2, this.E.keySet());
         bhr.pC(var2, this.sY);
      }

      return var2;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.UT == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.UT = (IJavaExpression)var2;
            return true;
         }
      } else {
         if (this.oT != null) {
            for (int var4 : this.oT.keySet()) {
               IJavaElement var5 = (IJavaElement)this.oT.get(var4);
               if (var5 == var1) {
                  if (var2 == null) {
                     return false;
                  }

                  this.oT.put(var4, var2);
                  return true;
               }
            }
         }

         for (bir var7 : this.E.keySet()) {
            if (var7 == var1) {
               throw new RuntimeException("Switch-case block replacement is not supported: use IJavaSwitch methods to modify this expression");
            }
         }

         if (this.sY == var1) {
            if (var2 != null && !(var2 instanceof bir)) {
               return false;
            }

            this.sY = (bir)var2;
         }

         return false;
      }
   }

   private List kS(JavaOutputSink var1) {
      ArrayList var2 = new ArrayList(this.E.keySet());

      try {
         Collections.sort(var2, new bkd(this));
      } catch (IllegalArgumentException var11) {
         IllegalArgumentException var3 = var11;

         StringBuilder var5;
         try {
            var5 = new StringBuilder("Potential illegal switch; blocks= ");
            int var6 = 0;

            for (Entry var8 : this.E.entrySet()) {
               if (var6 >= 1) {
                  var5.append(",");
               }

               Strings.ff(var5, "%d:%s", var6, Strings.joinList((Iterable)var8.getValue()));
               var6++;
            }

            new JebRuntimeException(var5.toString(), var3);
         } catch (Exception var10) {
            var5 = new StringBuilder(Strings.ff("Unexpected error: %s", var10));
         }

         JebRuntimeException var4 = new JebRuntimeException(var5.toString(), var11);
         String var12 = null;
         if (var1 != null) {
            try {
               var12 = var1.getCurrentContainingMethod().getSignature();
            } catch (Exception var9) {
            }
         }

         com.pnfsoftware.jeb.corei.parsers.dexdec.rQ.pC(var4, var12);
         var2 = new ArrayList(this.E.keySet());
      }

      return var2;
   }

   public void pC(Map var1) {
      this.oT = var1;
   }

   public Map kS() {
      return this.oT;
   }

   @Override
   public void reset() {
      this.UT = null;
      this.E.clear();
      this.sY = null;
      if (this.ys != null) {
         this.ys.clear();
      }

      if (this.gp != null) {
         this.gp.clear();
      }

      if (this.oT != null) {
         this.oT.clear();
      }
   }

   @Override
   public void reset(int var1) {
      this.UT = null;
      this.E.clear();
      this.sY = null;
      if (var1 == 0) {
         this.ys = null;
         this.gp = null;
      } else if (var1 == 1) {
         this.ys = null;
         this.gp = new HashMap();
      } else {
         if (var1 != 2) {
            throw new RuntimeException();
         }

         this.ys = new HashMap();
         this.gp = null;
      }

      this.oT = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.pC(var1);
      IDynamicContentManager var2 = var1.getDynamicContentManager();
      var1.appendKeyword(JavaKeyword.SWITCH);
      var1.paren();
      this.UT.generate(var1);
      var1.parenClose();
      var1.space();
      var1.brace();
      var1.eol();
      var1.incrementIndentationLevel();

      for (bir var5 : this.kS(var1)) {
         List var6 = (List)this.E.get(var5);
         Collections.sort(var6);
         int var7 = 0;

         for (int var9 : var6) {
            if (var7 >= 1) {
               var1.eol();
            }

            var1.appendKeyword(JavaKeyword.CASE);
            var1.space();
            if (this.ys == null) {
               if (this.gp != null && this.gp.containsKey(var9)) {
                  String var12 = (String)this.gp.get(var9);
                  IJavaField var15 = var1.getDecompilerUnit() == null ? null : var1.getDecompilerUnit().getField(var12, true);
                  if (var15 == null) {
                     var1.appendAndRecord(JvmFieldSig.parse(var12).fname, ItemClassIdentifiers.FIELD_NAME);
                  } else {
                     var15.generateName(var1, false);
                  }
               } else {
                  String var10 = null;
                  if (var2 != null) {
                     var10 = var2.retrieveImmediateHint(var9 & 4294967295L);
                  }

                  if (this.oT != null && this.oT.containsKey(var9)) {
                     IJavaElement var14 = (IJavaElement)this.oT.get(var9);
                     var14.generate(var1);
                  } else {
                     String var11;
                     if (var10 != null) {
                        var11 = Strings.ff("0x%X", var9);
                     } else {
                        var11 = Strings.ff("%d", var9);
                     }

                     var1.appendAndRecord(var11, ItemClassIdentifiers.NUMBER);
                  }

                  if (var10 != null) {
                     var1.setEolComment(var10, true);
                  }
               }
            } else {
               String var13 = (String)this.ys.get(var9);
               if (var13 == null) {
                  var13 = "unknown_stringkey_value_" + var9;
               }

               var1.appendAndRecord("\"" + var13 + "\"", ItemClassIdentifiers.STRING);
            }

            var1.append(": ");
            var7++;
         }

         var5.generate(var1);
         var1.eol();
      }

      if (this.sY != null) {
         var1.appendKeyword(JavaKeyword.DEFAULT);
         var1.append(": ");
         this.sY.generate(var1);
         var1.eol();
      }

      var1.decrementIndentationLevel();
      var1.braceClose();
      this.A(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Switch;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.E == null ? 0 : this.E.hashCode());
      var1 = 31 * var1 + (this.sY == null ? 0 : this.sY.hashCode());
      var1 = 31 * var1 + (this.ys == null ? 0 : this.ys.hashCode());
      return 31 * var1 + (this.UT == null ? 0 : this.UT.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         bkc var2 = (bkc)var1;
         if (this.E == null) {
            if (var2.E != null) {
               return false;
            }
         } else if (!this.E.equals(var2.E)) {
            return false;
         }

         if (this.sY == null) {
            if (var2.sY != null) {
               return false;
            }
         } else if (!this.sY.equals(var2.sY)) {
            return false;
         }

         if (this.ys == null) {
            if (var2.ys != null) {
               return false;
            }
         } else if (!this.ys.equals(var2.ys)) {
            return false;
         }

         if (this.UT == null) {
            if (var2.UT != null) {
               return false;
            }
         } else if (!this.UT.equals(var2.UT)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "switch(" + this.UT + ") {...}";
   }

   public bkc wS() {
      throw new RuntimeException("Duplication of try-statements is not supported");
   }
}
