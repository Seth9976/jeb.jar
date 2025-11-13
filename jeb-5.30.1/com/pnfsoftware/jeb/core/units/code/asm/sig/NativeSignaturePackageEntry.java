package com.pnfsoftware.jeb.core.units.code.asm.sig;

import com.pnfsoftware.jeb.util.format.Strings;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class NativeSignaturePackageEntry {
   private File file;
   private NativeSignaturePackageMetadata metadata;
   private List signaturesToWrite;
   private int openHandle;
   private NativeSignaturePackageEntry.PackageStatus status;

   public NativeSignaturePackageEntry(File var1, NativeSignaturePackageMetadata var2) {
      this.file = var1;
      this.metadata = var2;
   }

   public long getSize() {
      return this.file.length();
   }

   public NativeSignaturePackageEntry.PackageStatus getStatus() {
      return this.status;
   }

   public void setStatus(NativeSignaturePackageEntry.PackageStatus var1) {
      this.status = var1;
   }

   public boolean isActive() {
      return this.status == NativeSignaturePackageEntry.PackageStatus.ACTIVE;
   }

   public boolean isLoadedInMemory() {
      return this.openHandle > 0;
   }

   public void incrementOpenHandle() {
      this.openHandle++;
   }

   public void decrementOpenHandle() {
      this.openHandle--;
   }

   public File getFile() {
      return this.file;
   }

   public NativeSignaturePackageMetadata getMetadata() {
      return this.metadata;
   }

   public boolean isUserCreated() {
      return this.metadata.getUuid() < 0;
   }

   public void clearSignaturesToWrite() {
      this.signaturesToWrite.clear();
   }

   public void addSignatureToWrite(INativeSignature var1) {
      if (this.signaturesToWrite == null) {
         this.signaturesToWrite = new ArrayList();
      }

      this.signaturesToWrite.add(var1);
   }

   public List getSignatureToWrite() {
      return this.signaturesToWrite;
   }

   @Override
   public String toString() {
      return Strings.ff("Status:%s|Header=%s|File:%s", this.getStatus(), this.getMetadata(), this.getFile());
   }

   public static enum PackageStatus {
      INACTIVE,
      ACTIVE;
   }
}
