/*
 * This file is part of NeptuneVanilla, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2017, Jamie Mansfield <https://github.com/jamierocks>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.neptunepowered.vanilla.mixin.core.entity.ai.attributes;

import net.canarymod.api.attributes.AttributeModifier;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Intrinsic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.UUID;

@Mixin(net.minecraft.entity.ai.attributes.AttributeModifier.class)
@Implements(@Interface(iface = AttributeModifier.class, prefix = "modifier$"))
public abstract class MixinAttributeModifier implements AttributeModifier {

    @Shadow @Final private UUID id;

    @Shadow public abstract net.minecraft.entity.ai.attributes.AttributeModifier shadow$setSaved(boolean saved);
    @Shadow public abstract String getName();
    @Shadow public abstract int getOperation();
    @Shadow public abstract double getAmount();
    @Shadow public abstract boolean isSaved();

    @Override
    public UUID getUUID() {
        return this.id;
    }

    @Intrinsic
    public String modifier$getName() {
        return this.getName();
    }

    @Intrinsic
    public int modifier$getOperation() {
        return this.getOperation();
    }

    @Intrinsic
    public double modifier$getAmount() {
        return this.getAmount();
    }

    @Intrinsic
    public boolean modifier$isSaved() {
        return this.isSaved();
    }

    @Override
    public AttributeModifier setSaved(boolean saved) {
        return (AttributeModifier) this.shadow$setSaved(saved);
    }

}
