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
public class bbo implements ICodeNode {
   @SerId(1)
   private axj q;
   @SerId(2)
   private bbo RF;
   @SerId(3)
   private volatile List xK;
   @SerId(4)
   private volatile boolean Dw;

   public bbo(axj var1) {
      Assert.a(var1 != null, "The native item wrapped in a code node cannot be null");
      this.q = var1;
   }

   public void q(boolean var1) {
      this.Dw = var1;
   }

   @Override
   public int getInitialExpansion() {
      return this.Dw ? 1 : 0;
   }

   @Override
   public String getLabel() {
      String var1 = this.q.getName(true);
      if (var1 != null && this.RF != null && TypeUtil.containsCppSeparator(var1)) {
         axj var2 = this.RF.RF();
         if (var2 instanceof bbp && var2.getName() != null) {
            String var3 = CodeNodeUtil.getPackageNameFromHierarchy(this.RF);
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

   public bbo q() {
      return this.RF;
   }

   public synchronized void q(bbo var1) {
      if (this.RF != null) {
         this.RF.xK(this);
      }

      this.RF = var1;
      if (var1 != null) {
         var1.RF(this);
      }
   }

   @Override
   public boolean hasChildren() {
      return this.xK == null ? false : !this.xK.isEmpty();
   }

   @Override
   public int getCountOfChildren() {
      return this.xK == null ? 0 : this.xK.size();
   }

   @Override
   public List getChildren() {
      return this.xK == null ? Collections.emptyList() : Collections.unmodifiableList(this.xK);
   }

   public List q(Class var1) {
      if (this.xK == null) {
         return Collections.emptyList();
      } else {
         ArrayList var2 = new ArrayList();

         for (bbo var4 : this.xK) {
            axj var5 = var4.RF();
            Assert.a(var5 != null);
            if (var1.isInstance(var5)) {
               var2.add(var5);
            }
         }

         return var2;
      }
   }

   public bbo q(String var1, boolean var2) {
      if (this.xK == null) {
         return null;
      } else {
         bbo var3 = null;

         for (bbo var5 : this.xK) {
            axj var6 = var5.RF();
            if (var6 instanceof bbp && Strings.equals(((bbp)var6).getName(var2), var1)) {
               var3 = var5;
               break;
            }
         }

         return var3;
      }
   }

   public bbo q(String var1) {
      if (this.xK == null) {
         return null;
      } else {
         bbo var2 = null;

         for (bbo var4 : this.xK) {
            axj var5 = var4.RF();
            if (var5 instanceof bbp && (Strings.equals(((bbp)var5).getName(true), var1) || Strings.equals(((bbp)var5).getName(false), var1))) {
               var2 = var4;
               break;
            }
         }

         return var2;
      }
   }

   public bbo q(ICodeItem var1) {
      if (var1 == this.RF()) {
         return this;
      } else if (this.xK == null) {
         return null;
      } else {
         bbo var2 = null;

         for (bbo var4 : this.xK) {
            bbo var5 = var4.q(var1);
            if (var5 != null) {
               var2 = var5;
               break;
            }
         }

         return var2;
      }
   }

   public synchronized void RF(bbo var1) {
      if (this.xK == null) {
         this.xK = new CopyOnWriteArrayList();
      } else if (this.xK.contains(var1)) {
         return;
      }

      if (var1.RF != null && var1.RF != this) {
         throw new RuntimeException("Node already has a parent, cannot be added as a child of another node");
      } else {
         this.xK.add(var1);
         var1.RF = this;
      }
   }

   public synchronized void xK(bbo var1) {
      if (this.xK != null) {
         if (this.xK.contains(var1)) {
            this.xK.remove(var1);
            var1.RF = null;
         }
      }
   }

   public axj RF() {
      return this.q;
   }

   @Override
   public ItemClassIdentifiers getClassId() {
      return null;
   }

   @Override
   public long getItemId() {
      return this.q.getItemId();
   }

   @Override
   public int getItemFlags() {
      return 0;
   }

   @Override
   public String toString() {
      return Strings.ff("[%s]", this.q);
   }
}
