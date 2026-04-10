package com.arimattitoivonen.questlog;

import com.arimattitoivonen.questlog.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class QuestlogApplication {

	private static final Logger log = LoggerFactory.getLogger(QuestlogApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(QuestlogApplication.class, args);
	}

	@Bean
	public CommandLineRunner exampleData(CampaignRepository campaignRepository,
										 GameRepository gameRepository,
										 GenreRepository genreRepository,
										 SessionRepository sessionRepository,
										 AppUserRepository appUserRepository) {
		return (args) -> {
			log.info("Creating example genres and games");

			Genre fantasy = genreRepository.save(new Genre("Fantasy", null));
			Genre scifi = genreRepository.save(new Genre("Science Fiction", null));
			Genre horror = genreRepository.save(new Genre("Horror", null));
			Genre osr = genreRepository.save(new Genre("OSR", null));
			Genre modern = genreRepository.save(new Genre("Modern", null));

			Game dnd = new Game("Dungeons & Dragons 5th edition", "Heroic fantasy roleplaying", List.of(fantasy), "2014");
			Game ose = new Game("Old-School Essentials", "Old-school fantasy roleplaying", List.of(fantasy,osr), "2019");
			Game coc = new Game("Call of Cthulhu 7th edition", "Horror roleplaying based on the stories of H.P. Lovecraft", List.of(horror), "2016");
			Game mosh = new Game("Mothership 1st edition", "Horror roleplaying in space", List.of(scifi, horror, osr), "2024");
			Game cairn = new Game("Cairn 2e", "Rules-lite fantasy roleplaying", List.of(fantasy, osr), "2025");
			Game ua = new Game("Unknown Armies 3rd edition", "Post-modern fantasy roleplaying", List.of(fantasy, modern), "2017");
			Game godbound = new Game("Godbound", "Game of fantasy demigods", List.of(fantasy), "2016");
			gameRepository.save(dnd);
			gameRepository.save(ose);
			gameRepository.save(coc);
			gameRepository.save(mosh);
			gameRepository.save(cairn);
			gameRepository.save(ua);
			gameRepository.save(godbound);

			log.info("Creating users, campaigns and sessions");

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			AppUser arttu = appUserRepository.save(new AppUser("arttu", encoder.encode("password"), Enums.UserRole.USER));
			AppUser demo  = appUserRepository.save(new AppUser("demo",  encoder.encode("password"), Enums.UserRole.USER));
			AppUser admin = appUserRepository.save(new AppUser("admin", encoder.encode("admin"),    Enums.UserRole.ADMIN));

			// Arttu's campaigns
			Campaign uaCampaign   = campaignRepository.save(new Campaign("Hotel California", "Strange happenings in NOLA", ua,   arttu, Enums.CampaignStatus.Active));
			Campaign dndCampaign  = campaignRepository.save(new Campaign("Khel", "What happens in Khel, stays in Khel",   dnd,  arttu, Enums.CampaignStatus.Completed));
			Campaign cairnCampaign = campaignRepository.save(new Campaign("The Forgotten Era", "Mystery of the Woods",    cairn, arttu, Enums.CampaignStatus.Active));

			sessionRepository.save(new Session(uaCampaign,  ua,   LocalDate.of(2024, 1, 12), 180, "First session, arrived in New Orleans",       Enums.SessionRole.Player, arttu));
			sessionRepository.save(new Session(uaCampaign,  ua,   LocalDate.of(2024, 1, 26), 210, "Investigated the mysterious disco",            Enums.SessionRole.Player, arttu));
			sessionRepository.save(new Session(uaCampaign,  ua,   LocalDate.of(2024, 2,  9), 195, "Entered the basement",                         Enums.SessionRole.Player, arttu));
			sessionRepository.save(new Session(dndCampaign, dnd,  LocalDate.of(2023, 6,  3), 240, "Players arrived in Khel",                      Enums.SessionRole.GM,     arttu));
			sessionRepository.save(new Session(dndCampaign, dnd,  LocalDate.of(2023, 6, 17), 220, "Players spent the entire session gambling",     Enums.SessionRole.GM,     arttu));
			sessionRepository.save(new Session(cairnCampaign, ose, LocalDate.of(2024, 3,  2), 180, "The town's temple is encased with a golden dome", Enums.SessionRole.GM,  arttu));
			sessionRepository.save(new Session(cairnCampaign, ose, LocalDate.of(2024, 3, 16), 200, "Players went to the woods searching for answers",  Enums.SessionRole.GM,  arttu));
			sessionRepository.save(new Session(null, mosh, LocalDate.of(2024, 2, 14), 240, "One-shot: The Haunting of Ypsilon-14", Enums.SessionRole.GM, arttu));

			// Demo user's campaigns
			Campaign moshrpg = campaignRepository.save(new Campaign("Dead Planet", "Stranded on a derelict vessel", mosh, demo, Enums.CampaignStatus.Active));
			Campaign cocCampaign = campaignRepository.save(new Campaign("Masks of Nyarlathotep", "Globe-trotting cosmic horror", coc, demo, Enums.CampaignStatus.Active));

			sessionRepository.save(new Session(moshrpg,    mosh, LocalDate.of(2024, 5,  4), 200, "Boarded the Alexis",                           Enums.SessionRole.GM,     demo));
			sessionRepository.save(new Session(moshrpg,    mosh, LocalDate.of(2024, 5, 18), 210, "Found the first body",                         Enums.SessionRole.GM,     demo));
			sessionRepository.save(new Session(moshrpg,    mosh, LocalDate.of(2024, 6,  1), 190, "Things in the cargo hold",                     Enums.SessionRole.GM,     demo));
			sessionRepository.save(new Session(cocCampaign, coc, LocalDate.of(2024, 4, 10), 240, "New York - gathering the investigators",        Enums.SessionRole.Player, demo));
			sessionRepository.save(new Session(cocCampaign, coc, LocalDate.of(2024, 4, 24), 230, "London - the Ju-Ju house",                     Enums.SessionRole.Player, demo));
			sessionRepository.save(new Session(null, godbound, LocalDate.of(2024, 3, 22), 180, "One-shot: The Throne of Salt", Enums.SessionRole.Player, demo));
		};
	}

}
