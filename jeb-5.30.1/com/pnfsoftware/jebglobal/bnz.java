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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;

@Ser
public class bnz extends bmi implements IJavaSwitch {
   private static final ILogger oW = GlobalLog.getLogger(bnz.class);
   @SerId(1)
   private IJavaExpression gO;
   @SerId(2)
   private Map nf;
   @SerId(3)
   private bmo gP;
   @SerId(4)
   private Map za;
   @SerId(value = 5, deprecated = true)
   @Deprecated
   private Map lm;
   @SerId(6)
   private Map zz;
   @SerId(7)
   private Map JY;

   @SerCustomInitPostGraph
   private void gO() {
      if (!(this.nf instanceof IdentityHashMap)) {
         this.nf = new IdentityHashMap(this.nf);
      }

      if (this.lm != null) {
         this.zz = new HashMap();
         this.lm.forEach((var1, var2) -> this.zz.put(var1, var2.getSignature()));
         this.lm = null;
      }
   }

   public bnz(IJavaExpression var1, int var2) {
      if (var1 == null) {
         throw new RuntimeException();
      } else {
         this.gO = var1;
         this.nf = new IdentityHashMap();
         this.gP = null;
         if (var2 == 1) {
            this.zz = new HashMap();
         } else if (var2 == 2) {
            this.za = new HashMap();
         } else if (var2 != 0) {
            throw new RuntimeException("Illegal switch type: " + var2);
         }
      }
   }

   public bnz(IJavaExpression var1) {
      this(var1, 0);
   }

   @Override
   public IJavaExpression getSwitchedExpression() {
      return this.gO;
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
         this.gO = var1;
      }
   }

   @Override
   public SortedSet getCaseKeys() {
      TreeSet var1 = new TreeSet();

      for (List var3 : this.nf.values()) {
         var1.addAll(var3);
      }

      return var1;
   }

   public SortedSet q(bmo var1) {
      return new TreeSet((Collection)this.nf.get(var1));
   }

   public SortedSet RF(bmo var1) {
      TreeSet var2 = new TreeSet();

      for (Integer var4 : (List)this.nf.get(var1)) {
         var2.add((String)this.za.get(var4));
      }

      return var2;
   }

   @Override
   public void convertToSwitchOnInteger(IJavaExpression var1) {
      if (var1 != null) {
         this.gO = var1;
      }

      this.za = null;
      this.zz = null;
   }

   @Override
   public void convertToSwitchOnEnum(IJavaExpression var1, Map var2) {
      if (var1 != null) {
         this.gO = var1;
      }

      this.za = null;
      this.zz = var2;
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
            this.gO = var1;
         }

         this.za = var2;
         this.zz = null;
      }
   }

   @Override
   public boolean isSwitchOnInteger() {
      return this.za == null && this.zz == null;
   }

   @Override
   public boolean isSwitchOnEnum() {
      return this.za == null && this.zz != null;
   }

   @Override
   public boolean isSwitchOnString() {
      return this.za != null && this.zz == null;
   }

   private void nf() {
      if (!this.isSwitchOnInteger()) {
         throw new IllegalStateException("Not a switch-on-int");
      }
   }

   private void gP() {
      if (!this.isSwitchOnEnum()) {
         throw new IllegalStateException("Not a switch-on-enum");
      }
   }

   private void za() {
      if (!this.isSwitchOnString()) {
         throw new IllegalStateException("Not a switch-on-string");
      }
   }

   @Override
   public Map getEnumMap() {
      this.gP();
      return this.zz;
   }

   @Override
   public Map getStringMap() {
      this.za();
      return this.za;
   }

   @Override
   public List getCaseBodies() {
      return new ArrayList(this.nf.keySet());
   }

   public bmo q(int var1) {
      for (bmo var3 : this.nf.keySet()) {
         if (((List)this.nf.get(var3)).contains(var1)) {
            return var3;
         }
      }

      return null;
   }

   public bmo RF(String var1) {
      this.za();
      Integer var2 = null;

      for (Entry var4 : this.za.entrySet()) {
         if (((String)var4.getValue()).equals(var1)) {
            var2 = (Integer)var4.getKey();
            break;
         }
      }

      return var2 == null ? null : this.q(var2);
   }

   @Override
   public boolean hasDefaultBlock() {
      return this.gP != null;
   }

   public bmo Dw() {
      return this.gP;
   }

   @Override
   public void addCase(List var1, IJavaBlock var2) {
      if (var2 != null && !var1.isEmpty()) {
         this.nf.put((bmo)var2, var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   public void q(List var1, IJavaBlock var2) {
      for (Object var4 : var1) {
         if (var4 instanceof Integer) {
            List var5 = Maps.addMulti(this.nf, (bmo)var2);
            var5.add((Integer)var4);
         } else if (var4 instanceof String) {
            int var7 = this.za.size();
            this.za.put(var7, (String)var4);
            List var6 = Maps.addMulti(this.nf, (bmo)var2);
            var6.add(var7);
         }
      }
   }

   @Override
   public void addCase(Map var1, IJavaBlock var2) {
      this.za();
      if (var2 != null && !var1.isEmpty()) {
         this.nf.put((bmo)var2, new ArrayList(var1.keySet()));
         this.za.putAll(var1);
      } else {
         throw new IllegalArgumentException();
      }
   }

   @Override
   public void setDefaultBlock(IJavaBlock var1) {
      this.gP = (bmo)var1;
   }

   @Override
   public boolean insertAt(int var1, IJavaStatement var2) {
      for (bmo var4 : this.nf.keySet()) {
         if (var4.insertAt(var1, var2)) {
            return true;
         }
      }

      return this.gP != null ? this.gP.insertAt(var1, var2) : false;
   }

   @Override
   public List getBlocks() {
      ArrayList var1 = new ArrayList(this.nf.keySet());
      if (this.gP != null) {
         var1.add(this.gP);
      }

      return var1;
   }

   @Override
   public List generateFlatList() {
      ArrayList var1 = new ArrayList();
      var1.add(new bsd(this));

      for (bmo var4 : this.xK(null)) {
         var1.add(new bse((List)this.nf.get(var4), this.isSwitchOnString() ? new ArrayList(this.RF(var4)) : null));
         var1.addAll(var4.generateFlatList());
      }

      if (this.gP != null) {
         var1.add(new bsf());
         var1.addAll(this.gP.generateFlatList());
      }

      var1.add(new bsg());
      return var1;
   }

   @Override
   public List getSubElements(boolean var1) {
      List var2 = blo.q(this.gO);
      if (this.JY != null) {
         blo.q(var2, this.JY.values());
      }

      if (!var1) {
         blo.q(var2, this.nf.keySet());
         blo.q(var2, this.gP);
      }

      return var2;
   }

   @Override
   public boolean replaceSubElement(IJavaElement var1, IJavaElement var2) {
      if (this.gO == var1) {
         if (!(var2 instanceof IJavaExpression)) {
            return false;
         } else {
            this.gO = (IJavaExpression)var2;
            return true;
         }
      } else {
         if (this.JY != null) {
            for (int var4 : this.JY.keySet()) {
               IJavaElement var5 = (IJavaElement)this.JY.get(var4);
               if (var5 == var1) {
                  if (var2 == null) {
                     return false;
                  }

                  this.JY.put(var4, var2);
                  return true;
               }
            }
         }

         for (bmo var7 : this.nf.keySet()) {
            if (var7 == var1) {
               throw new RuntimeException("Switch-case block replacement is not supported: use IJavaSwitch methods to modify this expression");
            }
         }

         if (this.gP == var1) {
            if (var2 != null && !(var2 instanceof bmo)) {
               return false;
            }

            this.gP = (bmo)var2;
         }

         return false;
      }
   }

   private List xK(JavaOutputSink var1) {
      ArrayList var2 = new ArrayList(this.nf.keySet());

      try {
         Collections.sort(var2, new boa(this));
      } catch (IllegalArgumentException var11) {
         IllegalArgumentException var3 = var11;

         StringBuilder var5;
         try {
            var5 = new StringBuilder("Potential illegal switch; blocks= ");
            int var6 = 0;

            for (Entry var8 : this.nf.entrySet()) {
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

         com.pnfsoftware.jeb.corei.parsers.dexdec.tw.q(var4, var12);
         var2 = new ArrayList(this.nf.keySet());
      }

      return var2;
   }

   public void q(Map var1) {
      this.JY = var1;
   }

   public Map Uv() {
      return this.JY;
   }

   @Override
   public void reset() {
      this.gO = null;
      this.nf.clear();
      this.gP = null;
      if (this.za != null) {
         this.za.clear();
      }

      if (this.zz != null) {
         this.zz.clear();
      }

      if (this.JY != null) {
         this.JY.clear();
      }
   }

   @Override
   public void reset(int var1) {
      this.gO = null;
      this.nf.clear();
      this.gP = null;
      if (var1 == 0) {
         this.za = null;
         this.zz = null;
      } else if (var1 == 1) {
         this.za = null;
         this.zz = new HashMap();
      } else {
         if (var1 != 2) {
            throw new RuntimeException();
         }

         this.za = new HashMap();
         this.zz = null;
      }

      this.JY = null;
   }

   @Override
   public void generate(JavaOutputSink var1) {
      this.q(var1);
      IDynamicContentManager var2 = var1.getDynamicContentManager();
      var1.appendKeyword(JavaKeyword.SWITCH);
      var1.paren();
      this.gO.generate(var1);
      var1.parenClose();
      var1.space();
      var1.brace();
      var1.eol();
      var1.incrementIndentationLevel();

      for (bmo var5 : this.xK(var1)) {
         List var6 = (List)this.nf.get(var5);
         Collections.sort(var6);
         int var7 = 0;

         for (int var9 : var6) {
            if (var7 >= 1) {
               var1.eol();
            }

            var1.appendKeyword(JavaKeyword.CASE);
            var1.space();
            if (this.za == null) {
               if (this.zz != null && this.zz.containsKey(var9)) {
                  String var12 = (String)this.zz.get(var9);
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

                  if (this.JY != null && this.JY.containsKey(var9)) {
                     IJavaElement var14 = (IJavaElement)this.JY.get(var9);
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
               String var13 = (String)this.za.get(var9);
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

      if (this.gP != null) {
         var1.appendKeyword(JavaKeyword.DEFAULT);
         var1.append(": ");
         this.gP.generate(var1);
         var1.eol();
      }

      var1.decrementIndentationLevel();
      var1.braceClose();
      this.RF(var1);
   }

   @Override
   public JavaElementType getElementType() {
      return JavaElementType.Switch;
   }

   @Override
   public int hashCode() {
      int var1 = 1;
      var1 = 31 * var1 + (this.nf == null ? 0 : this.nf.hashCode());
      var1 = 31 * var1 + (this.gP == null ? 0 : this.gP.hashCode());
      var1 = 31 * var1 + (this.za == null ? 0 : this.za.hashCode());
      return 31 * var1 + (this.gO == null ? 0 : this.gO.hashCode());
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
         bnz var2 = (bnz)var1;
         if (this.nf == null) {
            if (var2.nf != null) {
               return false;
            }
         } else if (!this.nf.equals(var2.nf)) {
            return false;
         }

         if (this.gP == null) {
            if (var2.gP != null) {
               return false;
            }
         } else if (!this.gP.equals(var2.gP)) {
            return false;
         }

         if (this.za == null) {
            if (var2.za != null) {
               return false;
            }
         } else if (!this.za.equals(var2.za)) {
            return false;
         }

         if (this.gO == null) {
            if (var2.gO != null) {
               return false;
            }
         } else if (!this.gO.equals(var2.gO)) {
            return false;
         }

         return true;
      }
   }

   @Override
   public String toString() {
      return "switch(" + this.gO + ") {...}";
   }

   public bnz oW() {
      throw new RuntimeException("Duplication of try-statements is not supported");
   }
}
