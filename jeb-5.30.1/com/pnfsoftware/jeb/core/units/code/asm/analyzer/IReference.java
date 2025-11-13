package com.pnfsoftware.jeb.core.units.code.asm.analyzer;

public interface IReference {
   int FLAG_DYNAMIC = 1;
   int FLAG_ADVANCED_ANALYSIS = 2;

   ReferenceLocation getFrom();

   ReferenceLocation getTo();

   ReferenceType getType();

   int getFlags();

   String getStringType();
}
