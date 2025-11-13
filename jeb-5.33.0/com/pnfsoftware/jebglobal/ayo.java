package com.pnfsoftware.jebglobal;

import com.pnfsoftware.jeb.core.output.ItemClassIdentifiers;
import com.pnfsoftware.jeb.core.output.tree.CodeNodeUtil;
import com.pnfsoftware.jeb.core.output.tree.ICodeNode;
import com.pnfsoftware.jeb.core.units.code.ICodeItem;
import com.pnfsoftware.jeb.core.units.code.asm.type.TypeUtil;
import com.pnfsoftware.jeb.util.base.Assert;
import com.pnfsoftware.jeb.util.format.Strings;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;
import com.pnfsoftware.jeb.util.serialization.annotations.SerId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Ser
public class ayo implements ICodeNode {
   @SerId(1)
   private auo pC;
   @SerId(2)
   private ayo A;
   @SerId(3)
   private volatile List kS;
   @SerId(4)
   private volatile boolean wS;

   public ayo(auo var1) {
      Assert.a(var1 != null, "The native item wrapped in a code node cannot be null");
      this.pC = var1;
   }

   public void pC(boolean var1) {
      this.wS = var1;
   }

   @Override
   public int getInitialExpansion() {
      return this.wS ? 1 : 0;
   }

   @Override
   public String getLabel() {
      String var1 = this.pC.getName(true);
      if (var1 != null && this.A != null && TypeUtil.containsCppSeparator(var1)) {
         auo var2 = this.A.A();
         if (var2 instanceof ayp && var2.getName() != null) {
            String var3 = CodeNodeUtil.getPackageNameFromHierarchy(this.A);
            if (var3 != null) {
               List var4 = TypeUtil.splitCppName(var1);
               if (!var4.isEmpty()) {
                  String var5 = (String)var4.remove(var4.size() - 1);
                  String var6 = Strings.join("::", var4);
                  if (Strings.equals(var6, var3)) {
                     return var5;
                  }
               }
            }
         }
      }

      return var1;
   }

   @Override
   public String[] getAdditionalLabels() {
      return null;
   }

   public ayo pC() {
      return this.A;
   }

   public synchronized void pC(ayo var1) {
      if (this.A != null) {
         this.A.kS(this);
      }

      this.A = var1;
      if (var1 != null) {
         var1.A(this);
      }
   }

   @Override
   public boolean hasChildren() {
      return this.kS == null ? false : !this.kS.isEmpty();
   }

   @Override
   public int getCountOfChildren() {
      return this.kS == null ? 0 : this.kS.size();
   }

   @Override
   public List getChildren() {
      return this.kS == null ? Collections.emptyList() : Collections.unmodifiableList(this.kS);
   }

   public List pC(Class var1) {
      if (this.kS == null) {
         return Collections.emptyList();
      } else {
         ArrayList var2 = new ArrayList();

         for (ayo var4 : this.kS) {
            auo var5 = var4.A();
            Assert.a(var5 != null);
            if (var1.isInstance(var5)) {
               var2.add(var5);
            }
         }

         return var2;
      }
   }

   public ayo pC(String var1, boolean var2) {
      if (this.kS == null) {
         return null;
      } else {
         ayo var3 = null;

         for (ayo var5 : this.kS) {
            auo var6 = var5.A();
            if (var6 instanceof ayp && Strings.equals(((ayp)var6).getName(var2), var1)) {
               var3 = var5;
               break;
            }
         }

         return var3;
      }
   }

   public ayo pC(String var1) {
      if (this.kS == null) {
         return null;
      } else {
         ayo var2 = null;

         for (ayo var4 : this.kS) {
            auo var5 = var4.A();
            if (var5 instanceof ayp && (Strings.equals(((ayp)var5).getName(true), var1) || Strings.equals(((ayp)var5).getName(false), var1))) {
               var2 = var4;
               break;
            }
         }

         return var2;
      }
   }

   public ayo pC(ICodeItem var1) {
      if (var1 == this.A()) {
         return this;
      } else if (this.kS == null) {
         return null;
      } else {
         ayo var2 = null;

         for (ayo var4 : this.kS) {
            ayo var5 = var4.pC(var1);
            if (var5 != null) {
               var2 = var5;
               break;
            }
         }

         return var2;
      }
   }

   public synchronized void A(ayo var1) {
      if (this.kS == null) {
         this.kS = new CopyOnWriteArrayList();
      } else if (this.kS.contains(var1)) {
         return;
      }

      if (var1.A != null && var1.A != this) {
         throw new RuntimeException("Node already has a parent, cannot be added as a child of another node");
      } else {
         this.kS.add(var1);
         var1.A = this;
      }
   }

   public synchronized void kS(ayo var1) {
      if (this.kS != null) {
         if (this.kS.contains(var1)) {
            this.kS.remove(var1);
            var1.A = null;
         }
      }
   }

   public auo A() {
      return this.pC;
   }

   @Override
   public ItemClassIdentifiers getClassId() {
      return null;
   }

   @Override
   public long getItemId() {
      return this.pC.getItemId();
   }

   @Override
   public int getItemFlags() {
      return 0;
   }

   @Override
   public String toString() {
      return Strings.ff("[%s]", this.pC);
   }
}
