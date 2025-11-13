package com.pnfsoftware.jeb.core.units.code.asm.processor;

import com.pnfsoftware.jeb.core.units.code.asm.processor.arch.RegisterUtil;
import com.pnfsoftware.jeb.util.format.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public class RegisterDescriptionEntry {
   private int number;
   private int reg_grp;
   private int reg_idx;
   private long id0;
   private RegisterEncoding encoding;
   private RegisterType type;
   private int offset;
   private List slices = new ArrayList();
   private List names = new ArrayList();
   private int bitsize;
   private RegisterDescriptionEntry container;
   private int bitstart;

   public RegisterDescriptionEntry sl(String var1, int var2, int var3) {
      this.slices.add(new RegisterDescriptionEntry(var1, var2, var3, this));
      return this;
   }

   public RegisterDescriptionEntry sl(String var1, int var2) {
      return this.sl(var1, 0, var2);
   }

   private RegisterDescriptionEntry(String var1, int var2, int var3, RegisterDescriptionEntry var4) {
      if (var4 != null
         && var4.container == null
         && var1 != null
         && !var1.isEmpty()
         && !var4.names.contains(var1)
         && var2 >= 0
         && var3 <= var4.bitsize
         && var2 < var3) {
         this.addName(var1);
         this.bitstart = var2;
         this.bitsize = var3 - var2;
         this.container = var4;
      } else {
         throw new IllegalArgumentException();
      }
   }

   public RegisterDescriptionEntry(int var1, String var2, int var3, RegisterEncoding var4, String var5, RegisterType var6, int var7) {
      if (Strings.isBlank(var2)) {
         throw new IllegalArgumentException("Register name cannot be null or empty: " + var2);
      } else if (var3 <= 0) {
         throw new IllegalArgumentException("Illegal register bitsize: " + var3);
      } else {
         this.number = var1;
         this.addName(var2);
         this.bitsize = var3;
         this.reg_grp = -1;
         this.reg_idx = -1;
         this.id0 = -1L;
         this.addName(var5);
         this.encoding = var4 == null ? RegisterEncoding.unknown : var4;
         this.type = var6 == null ? RegisterType.Unspecified : var6;
         this.offset = var7;
      }
   }

   public boolean isPhysicalRegister() {
      return this.container == null;
   }

   public boolean isRegisterSlice() {
      return this.container != null;
   }

   public void verifyPhysicalRegister() {
      if (!this.isPhysicalRegister()) {
         throw new RuntimeException("Must be called on a physical register");
      }
   }

   public void verifyRegisterSlice() {
      if (!this.isRegisterSlice()) {
         throw new RuntimeException("Must be called on a register slice");
      }
   }

   public RegisterDescriptionEntry getContainer() {
      this.verifyRegisterSlice();
      return this.container;
   }

   public int getNumber() {
      return this.container != null ? this.container.number : this.number;
   }

   public long getId() {
      if (this.isPhysicalRegister()) {
         return this.id0 == -1L
            ? RegisterUtil.createRegisterIdFromPureId(this.getNumber(), this.bitsize, this.bitstart)
            : RegisterUtil.createRegisterIdFromPureId(this.id0, this.bitsize, this.bitstart);
      } else {
         return this.container.id0 == -1L
            ? RegisterUtil.createRegisterIdFromPureId(this.container.getNumber(), this.bitsize, this.bitstart)
            : RegisterUtil.createRegisterIdFromPureId(this.container.id0, this.bitsize, this.bitstart);
      }
   }

   public long getPureId() {
      this.verifyPhysicalRegister();
      return this.id0 == -1L ? this.getNumber() : this.id0;
   }

   public RegisterDescriptionEntry grp(int var1, int var2) {
      this.verifyPhysicalRegister();
      this.reg_grp = var1;
      this.reg_idx = var2;
      this.id0 = RegisterUtil.createPureRegisterId(this.reg_idx, this.reg_grp);
      return this;
   }

   public int getOffset() {
      return this.container != null ? this.container.offset : this.offset;
   }

   public void setOffset(int var1) {
      this.verifyPhysicalRegister();
      this.offset = var1;
   }

   public String getName() {
      return (String)this.names.get(0);
   }

   public List getNames() {
      return this.names;
   }

   public boolean hasName(String var1) {
      return this.hasName(var1, false);
   }

   public boolean hasName(String var1, boolean var2) {
      if (var2) {
         return this.names.contains(var1);
      } else {
         for (String var4 : this.names) {
            if (var4.equalsIgnoreCase(var1)) {
               return true;
            }
         }

         return false;
      }
   }

   public boolean hasName(Collection var1) {
      return this.hasName(var1, false);
   }

   public boolean hasName(Collection var1, boolean var2) {
      if (var2) {
         return CollectionUtils.containsAny(this.names, var1);
      } else {
         for (String var4 : this.names) {
            for (String var6 : var1) {
               if (var4.equalsIgnoreCase(var6)) {
                  return true;
               }
            }
         }

         return false;
      }
   }

   public RegisterDescriptionEntry addName(String var1) {
      if (var1 != null && !Strings.isBlank(var1) && !this.names.contains(var1)) {
         this.names.add(var1);
      }

      return this;
   }

   public int getBitsize() {
      return this.bitsize;
   }

   public int getBitstart() {
      return this.container != null ? this.bitstart : 0;
   }

   public int getBitend() {
      return this.container != null ? this.bitstart + this.bitsize : this.bitsize;
   }

   public List getSlices() {
      return this.slices;
   }

   public RegisterDescriptionEntry getSlice(int var1, int var2) {
      for (RegisterDescriptionEntry var4 : this.slices) {
         if (var4.getBitstart() == var1 && var4.getBitend() == var2) {
            return var4;
         }
      }

      return null;
   }

   public int getSize() {
      return (this.bitsize + 7) / 8;
   }

   public RegisterEncoding getEncoding() {
      return this.container != null ? this.container.encoding : this.encoding;
   }

   public RegisterDescriptionEntry enc(RegisterEncoding var1) {
      if (this.container != null) {
         throw new RuntimeException();
      } else {
         this.encoding = var1;
         return this;
      }
   }

   public RegisterType getType() {
      return this.container != null ? this.container.type : this.type;
   }

   public RegisterDescriptionEntry typ(RegisterType var1) {
      this.verifyPhysicalRegister();
      this.type = var1;
      return this;
   }

   @Override
   public String toString() {
      StringBuilder var1 = new StringBuilder();
      if (this.isRegisterSlice()) {
         Strings.ff(var1, "%d/SLICE:%s[%d-%d[", this.getNumber(), this.getName(), this.getBitstart(), this.getBitend());
      } else {
         Strings.ff(var1, "%d:%s(%d)", this.getNumber(), this.getName(), this.getBitsize());
      }

      if (this.names.size() >= 2) {
         Strings.ff(var1, "[%s]", Strings.join(",", this.names.subList(1, this.names.size())));
      }

      if (this.getType() != RegisterType.Unspecified) {
         Strings.ff(var1, "/%s", this.getType());
      }

      if (this.getEncoding() != RegisterEncoding.unknown) {
         Strings.ff(var1, "/%s", this.getEncoding());
      }

      if (this.getOffset() >= 0) {
         Strings.ff(var1, "@%Xh", this.getOffset());
      }

      return var1.toString();
   }
}
