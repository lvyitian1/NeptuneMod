/*
 * This file is part of NeptuneVanilla, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2016, Jamie Mansfield <https://github.com/jamierocks>
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
package org.neptunepowered.vanilla;

import co.aikar.timings.NeptuneTimingsFactory;
import co.aikar.timings.Timings;
import net.canarymod.Canary;
import net.minecraft.server.MinecraftServer;
import org.neptunepowered.vanilla.util.ReflectionUtil;

public final class NeptuneMain {

    public static void main(String[] args) throws Exception {
        // Some handy messages that CanaryMod had
        Canary.log.info("Starting: " + Canary.getImplementationTitle() + " " + Canary.getImplementationVersion());
        Canary.log.info("Neptune Path: " + Canary.getCanaryJarPath() + " & Working From: " + Canary.getWorkingPath());

        // First lets get Timings up and running
        initTimings();

        // Initialise the sqlite jdbc driver
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException ignored) {
        }

        // Now lets initialise Neptune / Canary
        initNeptune();

        // Now we're ready to start the Minecraft server
        MinecraftServer.main(args);
    }

    private static void initTimings() throws Exception {
        NeptuneTimingsFactory timingsFactory = new NeptuneTimingsFactory();
        ReflectionUtil.setStaticFinalField(Timings.class, "factory", timingsFactory);
        timingsFactory.init();
    }

    private static void initNeptune() {
        new Neptune();
    }

}