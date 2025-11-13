package com.pnfsoftware.jeb.core.units.code.simatic;

import com.pnfsoftware.jeb.core.units.code.asm.type.INativeType;
import com.pnfsoftware.jeb.core.units.code.asm.type.ITypeManager;
import com.pnfsoftware.jeb.util.serialization.annotations.Ser;

@Ser
public interface IS7Block {
   int getBlockFormatVersion();

   int getSourceLanguageId();

   S7.LangType getSourceLanguage();

   int getTypeId();

   S7.BlockType getType();

   boolean isInstanceDataBlock();

   int getNumber();

   int getBlockSize();

   byte[] getBlockBytes();

   int getBlockByte(int var1);

   int getTrailerOffset();

   int getPayloadOffset();

   int getPayloadSize();

   byte[] getPayloadBytes();

   int getInterfaceOffset();

   int getInterfaceSize();

   byte[] getInterfaceBytes();

   int getOtherOffset();

   int getOtherSize();

   byte[] getOtherBytes();

   long getModificationTimestamp();

   long getInterfaceModificationTimestamp();

   int getKey();

   String getMetadataBlockName();

   String getMetadataFamilyName();

   String getMetadataAuthorName();

   int[] getVersion();

   int getCrc();

   String getName();

   INativeType generateNativeHeaderType(ITypeManager var1);

   INativeType generateNativeTrailerType(ITypeManager var1);
}
