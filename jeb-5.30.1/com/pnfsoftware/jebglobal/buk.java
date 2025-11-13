package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.units.code.IInstruction;
import com.pnfsoftware.jeb.core.units.code.android.ir.DCopyOptions;
import com.pnfsoftware.jeb.core.units.code.android.ir.DFormattingContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDMethodContext;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDSwitchData;
import com.pnfsoftware.jeb.core.units.code.android.ir.IDTarget;
import com.pnfsoftware.jeb.util.format.Formatter;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.SerDisabled;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;

@SerDisabled
public class buk implements IDSwitchData {
   private TreeMap q = new TreeMap();

   @Override
   public void clear() {
      this.q.clear();
   }

   @Override
   public boolean isEmptySwitch() {
      return this.q.isEmpty();
   }

   @Override
   public boolean isRegularSwitch() {
      return this.q.isEmpty() || this.q.keySet().iterator().next() instanceof Integer;
   }

   @Override
   public boolean isStringSwitch() {
      return !this.q.isEmpty() && this.q.keySet().iterator().next() instanceof String;
   }

   @Override
   public boolean convertToStringKeys(Map var1) {
      if (!this.isRegularSwitch()) {
         throw new IllegalStateException();
      } else {
         TreeMap var2 = new TreeMap();

         for (Object var4 : this.q.keySet()) {
            String var5 = (String)var1.get(var4);
            if (var5 == null) {
               return false;
            }

            var2.put(var5, (IDTarget)this.q.get(var4));
         }

         this.q.clear();
         this.q.putAll(var2);
         return true;
      }
   }

   @Override
   public int hashCode() {
      byte var1 = 1;
      return 31 * var1 + (this.q == null ? 0 : this.q.hashCode());
   }

   @Override
   public boolean equals(Object var1) {
      return this.equalsEx(var1, false);
   }

   @Override
   public boolean equalsEx(Object var1, boolean var2) {
      if (this == var1) {
         return true;
      } else if (var1 == null) {
         return false;
      } else if (this.getClass() != var1.getClass()) {
         return false;
      } else {
         buk var3 = (buk)var1;
         if (this.q == null) {
            if (var3.q != null) {
               return false;
            }
         } else if (!this.q.equals(var3.q)) {
            return false;
         }

         return true;
      }
   }

   public buk q(DCopyOptions var1) {
      return this.q();
   }

   public buk q() {
      buk var1 = new buk();
      TreeMap var2 = new TreeMap();

      for (Entry var4 : this.q.entrySet()) {
         var2.put(var4.getKey(), var4.getValue() == null ? null : ((IDTarget)var4.getValue()).duplicate());
      }

      var1.q = var2;
      return var1;
   }

   @Override
   public IDTarget addCase(Object var1, IDTarget var2, boolean var3) {
      if (this.q.isEmpty()) {
         if (!(var1 instanceof Integer) && !(var1 instanceof String)) {
            throw new IllegalArgumentException("Expected an integer or string key");
         }
      } else {
         if (this.isRegularSwitch() && !(var1 instanceof Integer)) {
            throw new IllegalArgumentException("Expected an integer key");
         }

         if (this.isStringSwitch() && !(var1 instanceof String)) {
            throw new IllegalArgumentException("Expected a string key");
         }

         if (!var3 && this.q.containsKey(var1)) {
            throw new IllegalStateException("Duplicate key in switch table: " + var1);
         }
      }

      return (IDTarget)this.q.put(var1, var2);
   }

   @Override
   public int getCaseCount() {
      return this.q.size();
   }

   public TreeSet RF() {
      return new TreeSet(this.q.keySet());
   }

   public TreeSet xK() {
      if (!this.isRegularSwitch()) {
         throw new IllegalStateException();
      } else {
         TreeSet var1 = new TreeSet();

         for (Object var3 : this.q.keySet()) {
            var1.add((Integer)var3);
         }

         return var1;
      }
   }

   @Override
   public IDTarget getTargetForCase(Object var1) {
      return (IDTarget)this.q.get(var1);
   }

   @Override
   public int getTargetCount() {
      return new HashSet(this.q.values()).size();
   }

   private List Dw() {
      return new ArrayList(new LinkedHashSet(this.q.values()));
   }

   @Override
   public Collection getTargets(boolean var1) {
      return (Collection)(var1 ? this.Dw() : this.q.values());
   }

   @Override
   public List getKeysForTargets(IDTarget var1) {
      ArrayList var2 = new ArrayList();

      for (Object var4 : this.q.keySet()) {
         if (((IDTarget)this.q.get(var4)).equals(var1)) {
            var2.add(var4);
         }
      }

      return var2;
   }

   @Override
   public IDTarget deleteCase(Object var1) {
      return (IDTarget)this.q.remove(var1);
   }

   @Override
   public int deleteCasesToTarget(IDTarget var1) {
      return this.deleteCasesTo(var1.getOffset());
   }

   @Override
   public int deleteCasesTo(int var1) {
      ArrayList var2 = new ArrayList();

      for (Object var4 : this.q.keySet()) {
         if (((IDTarget)this.q.get(var4)).getOffset() == var1) {
            var2.add(var4);
         }
      }

      for (Object var6 : var2) {
         this.q.remove(var6);
      }

      return var2.size();
   }

   @Override
   public int updateCases(int var1, int var2) {
      int var3 = 0;

      for (Object var5 : this.q.keySet()) {
         if (((IDTarget)this.q.get(var5)).getOffset() == var1) {
            ((IDTarget)this.q.get(var5)).setOffset(var2);
            var3++;
         }
      }

      return var3;
   }

   @Override
   public int updateCases(Map var1, boolean var2) {
      int var3 = 0;

      for (Object var5 : this.q.keySet()) {
         int var6 = ((IDTarget)this.q.get(var5)).getOffset();
         Integer var7 = (Integer)var1.get(var6);
         if (var7 == null) {
            if (var2) {
               throw new IllegalArgumentException(Strings.ff("Missed entry for old offset 0x%X", var6));
            }
         } else {
            ((IDTarget)this.q.get(var5)).setOffset(var7);
            var3++;
         }
      }

      return var3;
   }

   @Override
   public String toString(IDMethodContext var1) {
      DFormattingContext var2 = new DFormattingContext(var1);
      this.format(var2);
      return var2.toString();
   }

   @Override
   public String toString() {
      return this.toString(null);
   }

   @Override
   public String format(IInstruction var1, long var2) {
      return this.toString();
   }

   @Override
   public void format(DFormattingContext var1) {
      var1.append('(');
      int var2 = 0;

      for (Object var4 : this.q.keySet()) {
         if (var2 >= 1) {
            var1.append(", ");
         }

         if (var4 instanceof String) {
            var1.append('"');
            var1.append(Formatter.escapeString((String)var4));
            var1.append('"');
         } else if (var4 instanceof Integer) {
            var1.append(Integer.toHexString((Integer)var4).toUpperCase());
         } else {
            var1.append(var4);
         }

         var1.append(':');
         IDTarget var5 = (IDTarget)this.q.get(var4);
         var5.format(var1);
         var2++;
      }

      var1.append(')');
   }
}
