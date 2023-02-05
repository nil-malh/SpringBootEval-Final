package fr.ipme.coupedumonde.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import fr.ipme.coupedumonde.entities.foot.Equipe;
import fr.ipme.coupedumonde.entities.foot.Groupe;
import fr.ipme.coupedumonde.entities.foot.Match;

import java.io.IOException;

public class GroupSerializer extends StdSerializer<Groupe> {
    public GroupSerializer() {
        this(null);
    }

    public GroupSerializer(Class<Groupe> t) {
        super(t);
    }

    @Override
    public void serialize(Groupe group, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("group_id", group.getId());
        jsonGenerator.writeArrayFieldStart("teams");
        for (Equipe equipe : group.getEquipes()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("team_id", equipe.getId());
            jsonGenerator.writeStringField("name", equipe.getName());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeArrayFieldStart("matches");
        for (Match match : group.getMatchs()) {
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("match_id", match.getId());
            jsonGenerator.writeNumberField("teamAId", match.getEquipeA().getId());
            jsonGenerator.writeNumberField("teamBId", match.getEquipeB().getId());
            jsonGenerator.writeStringField("match_date", match.getMatchDate().toString());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}