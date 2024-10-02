const mock1 = [
  {
    "name": "Sapiens: A Brief History of Humankind",
    "author": "Yuval Noah Harari",
    "spec": "History, Anthropology",
    "info": "An account of the 70,000-year history of Homo sapiens from the Stone Age to the Silicon Age. It explores how humans came to be the dominant species on Earth and questions where we might be heading.",
    "id": '1',
  },
  {
    "name": "The Selfish Gene",
    "author": "Richard Dawkins",
    "spec": "Evolutionary Biology",
    "info": "This book presents the gene-centered view of evolution and popularized the term meme. It explains how the genetic code determines our form and behavior and why the gene is the ultimate unit of natural selection."
  },
  {
    "name": "A Brief History of Time",
    "author": "Stephen Hawking",
    "spec": "Astrophysics",
    "info": "An exploration of the universe's origins, black holes, and the concept of time. It discusses complex topics in theoretical physics and cosmology in a way that is accessible to non-scientists."
  },
  {
    "name": "The Structure of Scientific Revolutions",
    "author": "Thomas S. Kuhn",
    "spec": "Philosophy of Science",
    "info": "A study of scientific paradigms and paradigm shifts, introducing the concept of normal science and scientific revolutions. It challenges traditional views of scientific progress as a linear process."
  },
  {
    "name": "The Origin of Species",
    "author": "Charles Darwin",
    "spec": "Natural History, Evolution",
    "info": "Darwin's seminal work on evolutionary theory by means of natural selection, which laid the foundation for modern evolutionary biology."
  },
  {
    "name": "The Double Helix",
    "author": "James D. Watson",
    "spec": "Molecular Biology",
    "info": "Watson's personal account of the discovery of the structure of DNA, providing insights into the scientific process and the personalities involved."
  },
  {
    "name": "Guns, Germs, and Steel: The Fates of Human Societies",
    "author": "Jared Diamond",
    "spec": "Anthropology, Geography",
    "info": "An investigation into the historical reasons why some societies have developed faster than others, leading to disparities in power and technology."
  },
  {
    "name": "The Emperor's New Mind",
    "author": "Roger Penrose",
    "spec": "Physics, Philosophy of Mind",
    "info": "Penrose's argument against the possibility of artificial intelligence, based on the limitations of formal logic systems as demonstrated by Gödel's incompleteness theorems."
  },
  {
    "name": "The Blind Watchmaker",
    "author": "Richard Dawkins",
    "spec": "Biology, Evolution",
    "info": "Dawkins' explanation of evolutionary processes, emphasizing the role of natural selection over other theories, and arguing against the idea of an intelligent designer."
  },
  {
    "name": "Cosmos",
    "author": "Carl Sagan",
    "spec": "Astronomy, Popular Science",
    "info": "Sagan's exploration of the cosmos and humanity's place within it, discussing the vastness of space, the origins of life, and the importance of scientific literacy."
  },
  {
    "name": "The Demon-Haunted World: Science as a Candle in the Dark",
    "author": "Carl Sagan",
    "spec": "Science, Skepticism",
    "info": "Sagan's call to arms for a scientifically literate society, urging readers to question claims and embrace critical thinking."
  },
  {
    "name": "The Elegant Universe: Superstrings, Hidden Dimensions, and the Quest for the Ultimate Theory",
    "author": "Brian Greene",
    "spec": "Physics, String Theory",
    "info": "Greene's introduction to string theory and the quest for a unified theory of physics, making complex ideas accessible."
  },
  {
    "name": "The Structure of Animal Communication",
    "author": "Trevor Halliday",
    "spec": "Ethology, Animal Behavior",
    "info": "An exploration of the ways animals communicate, including vocalizations, displays, and other signals used in the wild."
  },
  {
    "name": "The Moral Animal: Why We Are the Way We Are: The New Science of Evolutionary Psychology",
    "author": "Robert Wright",
    "spec": "Psychology, Ethics",
    "info": "Wright's examination of human nature through the lens of evolutionary psychology, explaining moral instincts and social behaviors."
  },
  {
    "name": "The Beak of the Finch: A Story of Evolution in Our Time",
    "author": "Jonathan Weiner",
    "spec": "Evolution, Ornithology",
    "info": "Weiner's narrative about the Grants' study of finches in the Galápagos Islands, showing evolution in action."
  },
  {
    "name": "The Physics of the Impossible: A Scientific Exploration into the World of Phasers, Force Fields, Cloaking Devices, Time Machines, and Warp Speed",
    "author": "Michio Kaku",
    "spec": "Physics, Speculative Science",
    "info": "Kaku's exploration of futuristic technologies and their basis in current scientific understanding."
  },
  {
    "name": "The Social Animal: The Hidden Sources of Love, Character, and Achievement",
    "author": "David Brooks",
    "spec": "Psychology, Sociology",
    "info": "Brooks' look at the invisible influences that shape human behavior, drawing on research in cognitive neuroscience and sociology."
  },
  {
    "name": "The Sixth Extinction: An Unnatural History",
    "author": "Elizabeth Kolbert",
    "spec": "Environment, Ecology",
    "info": "Kolbert's examination of the ongoing sixth mass extinction, caused by human activities and their impact on biodiversity."
  },
  {
    "name": "The Quantum World: Quantum Physics for Everyone",
    "author": "Kenneth W. Ford",
    "spec": "Quantum Mechanics",
    "info": "Ford's introduction to quantum mechanics, explaining the fundamental principles and bizarre phenomena of this branch of physics."
  },
  {
    "name": "The Information: A History, a Theory, a Flood",
    "author": "James Gleick",
    "spec": "Information Theory, History",
    "info": "Gleick's history of information, from ancient times to the digital age, and its profound impact on human culture."
  },
  {
    "name": "The Code Book: The Science of Secrecy from Ancient Egypt to Quantum Cryptography",
    "author": "Simon Singh",
    "spec": "Cryptography, History",
    "info": "Singh's history of codes and ciphers, from ancient times to modern cryptography, including the science behind keeping secrets."
  },
  {
    "name": "The Man Who Mistook His Wife for a Hat",
    "author": "Oliver Sacks",
    "spec": "Neurology, Case Studies",
    "info": "Sacks' collection of case studies exploring the strange and fascinating world of neurological disorders."
  },
  {
    "name": "The Ghost Map: The Story of London's Most Terrifying Epidemic - and How It Changed Science, Cities, and the Modern World",
    "author": "Steven Johnson",
    "spec": "Epidemiology, History",
    "info": "Johnson's recounting of the cholera outbreak in 1854 London and how it led to modern urban sanitation."
  },
  {
    "name": "The Emperor of All Maladies: A Biography of Cancer",
    "author": "Siddhartha Mukherjee",
    "spec": "Medical History, Oncology",
    "info": "Mukherjee's comprehensive history of cancer, detailing the disease's impact and the efforts to understand and treat it."
  },
  {
    "name": "The Selfish Society: How We Evolved to Cooperate",
    "author": "Nicholas A. Christakis",
    "spec": "Social Science, Evolutionary Psychology",
    "info": "Christakis' analysis of human cooperation, rooted in evolutionary theory and social network science."
  },
  {
    "name": "The Better Angels of Our Nature: Why Violence Has Declined",
    "author": "Steven Pinker",
    "spec": "Psychology, History",
    "info": "Pinker's argument that violence has decreased over the course of human history, supported by extensive data and analysis."
  },
  {
    "name": "The Language Instinct: How the Mind Creates Language",
    "author": "Steven Pinker",
    "spec": "Linguistics, Cognitive Science",
    "info": "Pinker's exploration of the innate human ability to acquire and use language, challenging the blank slate theory."
  },
  {
    "name": "The Infinite Monkey Cage: Where Science Meets Comedy",
    "author": "Brian Cox and Robin Ince",
    "spec": "Popular Science, Humor",
    "info": "Cox and Ince's humorous take on science, blending serious scientific discussion with comedy."
  },
  {
    "name": "The Alchemist",
    "author": "Paulo Coelho",
    "spec": "Fiction, Inspirational",
    "info": "Coelho's novel about a shepherd boy who embarks on a journey to fulfill his destiny, filled with wisdom and life lessons."
  },
  {
    "name": "The Structure of Scientific Communities",
    "author": "Harry Collins",
    "spec": "Sociology of Science",
    "info": "Collins' sociological study of scientific communities, focusing on how knowledge is produced and disseminated."
  }
];

const mock2 = [
  {
    "name": "The Righteous Mind: Why Good People Are Divided by Politics and Religion",
    "author": "Jonathan Haidt",
    "spec": "Psychology, Ethics",
    "info": "Haidt's exploration of the moral foundations of political beliefs and the psychological roots of ideological differences."
  },
  {
    "name": "The Elegant Universe: Superstrings, Hidden Dimensions, and the Quest for the Ultimate Theory",
    "author": "Brian Greene",
    "spec": "Physics, String Theory",
    "info": "Greene's introduction to the mysteries of superstring theory, extra dimensions, and the search for a unified theory of physics."
  },
  {
    "name": "The Drunkard's Walk: How Randomness Rules Our Lives",
    "author": "Leonard Mlodinow",
    "spec": "Probability, Statistics",
    "info": "Mlodinow's look at the role of randomness in everyday life and the misperceptions people have about chance events."
  },
  {
    "name": "The Signal and the Noise: Why So Many Predictions Fail—but Some Don't",
    "author": "Nate Silver",
    "spec": "Statistics, Prediction",
    "info": "Silver's analysis of prediction, using examples from politics, weather forecasting, and economics to illustrate how to distinguish signal from noise."
  },
  {
    "name": "The Botany of Desire: A Plant's-Eye View of the World",
    "author": "Michael Pollan",
    "spec": "Botany, Cultural History",
    "info": "Pollan's exploration of the relationship between humans and four plants—apples, tulips, cannabis, and potatoes—showing how they have co-evolved."
  },
  {
    "name": "The Second Creation: Makers of the Revolution in Twenty-First Century Biology",
    "author": "Robert Krulwich and Jad Abumrad",
    "spec": "Biology, Biotechnology",
    "info": "Krulwich and Abumrad's examination of the new era of synthetic biology and its potential to reshape the world."
  },
  {
    "name": "The Immortal Life of Henrietta Lacks",
    "author": "Rebecca Skloot",
    "spec": "Medical Ethics, Biographical",
    "info": "Skloot's story of the woman whose cells were taken without her knowledge and have been used in medical research ever since."
  },
  {
    "name": "The Story of Philosophy: The Lives and Opinions of the Greater Philosophers",
    "author": "Will Durant",
    "spec": "Philosophy, History",
    "info": "Durant's overview of the lives and contributions of major philosophers throughout history, written for the general reader."
  },
  {
    "name": "The Art of Computer Programming",
    "author": "Donald E. Knuth",
    "spec": "Computer Science",
    "info": "Knuth's multi-volume series on computer programming techniques and algorithms, considered a classic in the field."
  }
];



export {
  mock1,
  mock2
};